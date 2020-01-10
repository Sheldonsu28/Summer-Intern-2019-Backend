package com.mmm.weixin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mmm.weixin.dao.OrderFormMapper;
import com.mmm.weixin.dao.OrderFormPayMapper;
import com.mmm.weixin.dto.param.OrderDto;
import com.mmm.weixin.pay.*;
import com.mmm.weixin.service.PayService;
import com.mmm.weixin.utils.math.RandomUtils;
import com.mmm.weixin.vo.OrderForm;
import com.mmm.weixin.vo.OrderFormPay;
import com.mmm.weixin.vo.PayVo;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PayServiceImpl implements PayService {

    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
    private static final String resultStr = "<xml><return_code>SUCCESS</return_code><return_msg>OK</return_msg></xml>";

    @Autowired
    private OrderFormMapper orderFormMapper;

    @Autowired
    private OrderFormPayMapper orderFormPayMapper;

    @Override
    public JSONObject payOrderUnion(PayVo payVo) {

        try {
            OrderForm orderForm = orderFormMapper.selectByPrimaryKey(payVo.getOrderFormId());
            OrderInfo orderInfo = new OrderInfo();

            if (orderForm.getOrderFormType() == 3 && orderForm.getActualPayAmount().compareTo(BigDecimal.ZERO) == 0) {
                orderInfo.setOut_trade_no(String.valueOf(orderForm.getOrderFormId()));
                orderInfo.setTotal_fee(payVo.getPayAmount().multiply(new BigDecimal(100)).intValue());
            } else if (orderForm.getOrderFormType() == 3 && orderForm.getActualPayAmount().compareTo(orderForm.getShouldPayAmount()) != 0) {
                orderInfo.setOut_trade_no(payVo.getOrderFormCode());
                orderInfo.setTotal_fee((orderForm.getShouldPayAmount().subtract(orderForm.getActualPayAmount())).multiply(new BigDecimal(100)).intValue());
            } else {
                orderInfo.setOut_trade_no(payVo.getOrderFormCode());
                orderInfo.setTotal_fee(orderForm.getShouldPayAmount().multiply(new BigDecimal(100)).intValue());
            }

            orderInfo.setAppid(Configure.appID);
            orderInfo.setMch_id(Configure.mch_id);
            orderInfo.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
            orderInfo.setBody("尊享管家");
            orderInfo.setSpbill_create_ip("172.81.252.137");
            orderInfo.setNotify_url("https://api.cywl168.cn/order/payResult");
            orderInfo.setTrade_type("JSAPI");
            orderInfo.setOpenid(payVo.getOpenId());
            orderInfo.setSign_type("MD5");
            orderInfo.setSign(Signature.getSign(orderInfo));
            String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", orderInfo);
            logger.info("支付信息：" + orderInfo.toString());
            logger.info("微信下单：" + result);
            XStream xStream = new XStream();
            xStream.setClassLoader(Thread.currentThread().getContextClassLoader());
            xStream.alias("xml", OrderReturnInfo.class);
            OrderReturnInfo returnInfo = (OrderReturnInfo) xStream.fromXML(result);

            String prepayId = returnInfo.getPrepay_id();
            SignInfo signInfo = new SignInfo();
            signInfo.setAppId(Configure.appID);
            signInfo.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
            signInfo.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
            signInfo.setRepay_id("prepay_id=" + prepayId);
            signInfo.setSignType("MD5");
            String reSign = Signature.getSign(signInfo);
            JSONObject json = new JSONObject();
            json.put("timeStamp", signInfo.getTimeStamp());
            json.put("nonceStr", signInfo.getNonceStr());
            json.put("package", signInfo.getRepay_id());
            json.put("signType", signInfo.getSignType());
            json.put("paySign", reSign);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public String payResult(HttpServletRequest request) {

        try {
            String reqParams = StreamUtil.read(request.getInputStream());
            XStream xStream = new XStream();
            xStream.setClassLoader(Thread.currentThread().getContextClassLoader());
            xStream.alias("xml", PayResult.class);
            PayResult payResult = (PayResult) xStream.fromXML(reqParams);

            String out_trade_no = payResult.getOut_trade_no();
            Integer orderFormId = orderFormMapper.selectIdByCode(payResult.getOut_trade_no());
            if (orderFormId == null) {
                orderFormId = Integer.parseInt(out_trade_no);
            }

            OrderForm orderForm = orderFormMapper.selectByPrimaryKey(orderFormId);
            if (orderForm.getOrderFormType() == 3 && orderForm.getActualPayAmount().compareTo(BigDecimal.ZERO) != 0) {
                if (orderForm.getStateCode() == 4) {
                    return resultStr;
                }
                BigDecimal totalFee = orderForm.getActualPayAmount().add(new BigDecimal(payResult.getTotal_fee()).divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
                setOrderPayInfo(payResult, orderForm, totalFee, 4);
            } else {
                BigDecimal totalFee = new BigDecimal(payResult.getTotal_fee()).divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP);
                setOrderPayInfo(payResult, orderForm, totalFee, 3);
            }
            return resultStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setOrderPayInfo(PayResult payResult, OrderForm orderForm, BigDecimal totalFee, Integer stateCode) {

        try {
            OrderFormPay orderFormPay = new OrderFormPay();
            orderFormPay.setOrderformid(orderForm.getOrderFormId());
            orderFormPay.setPaycode(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.generateNumberString(6));
            orderFormPay.setPayamount(Integer.parseInt(payResult.getTotal_fee()));
            orderFormPay.setPaymethod(1);
            orderFormPay.setAppendtime(new Date());
            orderFormPay.setPaytime(new SimpleDateFormat("yyyyMMddHHmmss").parse(payResult.getTime_end()));
            orderFormPay.setPaycertificate(payResult.getTransaction_id());
            orderFormPay.setPayserialnumber(payResult.getTransaction_id());
            orderFormPay.setPayremark("");

            if (payResult != null && payResult.getReturn_code().equals("SUCCESS")) {
                orderFormMapper.updateOrderState(orderForm.getOrderFormId(), totalFee, stateCode);
                orderFormPay.setPaystatecode(100);
                orderFormPayMapper.insert(orderFormPay);
            } else {
                orderFormPay.setPaystatecode(0);
                orderFormPayMapper.insert(orderFormPay);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean getPayResult(OrderDto dto) {

        Integer payRet = orderFormMapper.getPayResult(dto);
        if (payRet != null && payRet == 3) {
            return true;
        }
        return false;
    }


}
