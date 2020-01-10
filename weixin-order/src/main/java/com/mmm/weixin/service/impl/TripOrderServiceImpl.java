package com.mmm.weixin.service.impl;

import com.mmm.weixin.common.TokenValidate;
import com.mmm.weixin.dao.*;
import com.mmm.weixin.dto.param.SaveTripOrderDto;
import com.mmm.weixin.service.TripOrderService;
import com.mmm.weixin.utils.sequence.GenerateSequenceUtil;
import com.mmm.weixin.vo.OrderForm;
import com.mmm.weixin.vo.OrderFormField;
import com.mmm.weixin.vo.OrderFormFieldValue;
import com.mmm.weixin.vo.OrderFormItems;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TripOrderServiceImpl implements TripOrderService {

    @Autowired
    private TokenValidate tokenValidate;

    @Autowired
    private TripOrderMapper tripOrderMapper;

    @Autowired
    private OrderFormItemsMapper orderItemsMapper;

    @Autowired
    private OrderFormFieldValueMapper orderFormFieldValueMapper;

    @Autowired
    private OrderFormFieldMapper orderFormFieldMapper;

    @Transactional
    @Override
    public OrderForm saveTripOrder(SaveTripOrderDto dto) {

        OrderForm orderForm = new OrderForm();
        BeanUtils.copyProperties(dto, orderForm);
        orderForm.setOrderFormCode(GenerateSequenceUtil.generateSequenceNo());
        orderForm.setUserId(tokenValidate.validateToken(dto.getAuthorization()));
        orderForm.setAppendTime(new Date());
        orderForm.setOrderFormType(2);
        orderForm.setLastTime(new Date());
        orderForm.setShouldPayAmount(dto.getTotalamount());
        orderForm.setActualPayAmount(new BigDecimal(0));
        orderForm.setPayMethod(1);
        orderForm.setStateCode(1);
        orderForm.setIsDelete(false);
        tripOrderMapper.SaveTripOrderMain(orderForm);

        OrderFormItems orderFormItems = new OrderFormItems();
        orderFormItems.setOrderformid(orderForm.getOrderFormId());
        orderFormItems.setSellprice(dto.getPrice());
        orderFormItems.setActualsellprice(dto.getPrice());
        orderFormItems.setTotalamount(dto.getTotalamount());
        orderFormItems.setCommodityid(dto.getCommodityid());
        orderFormItems.setSellcount(dto.getTravelcount());
        orderItemsMapper.insert(orderFormItems);

        addGolfOrderFormFieldValue(dto, orderForm.getOrderFormId());

        return orderForm;
    }

    public List<OrderFormField> listOrderFormFieldByOrderType(Integer orderFormType) {
        return orderFormFieldMapper.selectByOderType(orderFormType);
    }


    private void addGolfOrderFormFieldValue(SaveTripOrderDto param, Integer orderformid) {

        List<OrderFormField> orderFormFieds = this.listOrderFormFieldByOrderType(2);
        Iterator<OrderFormField> iterator = orderFormFieds.iterator();
        OrderFormFieldValue offv = null;
        while (iterator.hasNext()) {
            OrderFormField next = iterator.next();
            String fieldenname = next.getFieldenname();
            Integer fieldid = next.getFieldid();
            offv = new OrderFormFieldValue();
            offv.setFieldid(fieldid);
            offv.setOrderformid(orderformid);

            if ("TravelTitle".equals(fieldenname)) {
                offv.setValuetext(param.getCommodityname());
            } else if ("TravelDate".equals(fieldenname)) {
                offv.setValuetext(param.getTraveldate());
            } else if ("TravelCount".equals(fieldenname)) {
                offv.setValuetext(String.valueOf(param.getTravelcount()));
            } else if ("TravelFullName".equals(fieldenname)) {
                String[] playerName = param.getTravelfullName();
                String playerNameStr = String.join(",", playerName);
                offv.setValuetext(playerNameStr);
            } else if ("ContactPhone".equals(fieldenname)) {
                offv.setValuetext(String.valueOf(param.getContactphone()));
            } else if ("ContactName".equals(fieldenname)) {
                offv.setValuetext(String.valueOf(param.getContactname()));
            }
            orderFormFieldValueMapper.insert(offv);
        }
    }

}
