package com.mmm.weixin.service.impl;

import com.mmm.weixin.common.TokenValidate;
import com.mmm.weixin.dao.*;
import com.mmm.weixin.dto.InterUserTransactionDto;
import com.mmm.weixin.dto.PaymentDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.service.IPurseService;
import com.mmm.weixin.vo.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Service
public class PurseServiceImp implements IPurseService {
    @Autowired
    private UserPurseMapper purseMapper;
    @Autowired
    private UserSpentLogMapper spentMapper;
    @Autowired
    private UserChargeMapper chargeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenValidate tokenValidate;
    @Autowired
    private CommodityMapper commodityMapper;


    /**
     * 该方法负责用户之间的转账
     * @param transaction 转账用户双方的信息
     */
    @Transactional
    public void InterUserTransaction(InterUserTransactionDto transaction){
        User receiver = userMapper.selectByPhoneNumber(Long.parseLong(transaction.getReceiverPhoneNum()));
        if (receiver == null){                                                          //查询手机号是否能查找到用户
            throw new ServiceException("未找到与该手机号关联的用户");
        }
        UserPurse creatorPurse = purseMapper.selectByUid(transaction.getCreaterId());
        UserPurse receiverPurse = purseMapper.selectByUid(receiver.getUserId());
        if (receiverPurse == null){                                                     //收账人的钱包存在
            throw new ServiceException("该用户未开通钱包");
        }
        creatorPurse.setBalance(creatorPurse.getBalance() - transaction.getAmount());
        if (creatorPurse.getBalance() < 0){
            throw new ServiceException("您的余额不足！");                               //余额大于转账额度
        }else {
            receiverPurse.setBalance(receiverPurse.getBalance() + transaction.getAmount());
            //为发出者更新钱包并加入流水记录
            purseMapper.updateBalanceByPid(creatorPurse);
            UserSpentLog creatorLog = new UserSpentLog();
            creatorLog.setAmount(0 - transaction.getAmount());
            creatorLog.setPid(creatorPurse.getPid());
            creatorLog.setBusinessType(3);
            spentMapper.insertInterUserTransactionRecord(creatorLog);
            //为接收者更新钱包表并加入流水记录
            purseMapper.updateBalanceByPid(receiverPurse);
            UserSpentLog receiverLog = new UserSpentLog();
            receiverLog.setAmount(transaction.getAmount());
            receiverLog.setPid(receiverPurse.getPid());
            receiverLog.setBusinessType(2);
            spentMapper.insertInterUserTransactionRecord(receiverLog);
        }
    }

    /**
     * 查询用户钱包余额
     * @param request 请求
     * @return        用户钱包余额
     */
    public int getPurseBalance(HttpServletRequest request){
        String authorization = request.getHeader("authorization");
        int uid = tokenValidate.validateToken(authorization);
        return purseMapper.selectByUid(uid).getBalance();
    }


    @Transactional
    public Result userPay(PaymentDto payInfo, HttpServletRequest request){
        String authorization = request.getHeader("authorization");
        int uid = tokenValidate.validateToken(authorization);
        String OpenId = userMapper.selectByPrimaryKey(uid).getOpenId();
        UserPurse purse = purseMapper.selectByUid(payInfo.getUserId());
        if(purse == null){
            throw new ServiceException("您还未开通钱包");
        }
        Commodity commodity = commodityMapper.selectByPrimaryKey(payInfo.getCommodityId());
        if(commodity.getStatecode().equals(2)){
            throw new ServiceException("该商品已下架！");
        }
        if (payInfo.getPaymentType() == 1) {
            if (commodity.getSpecialPrice() != null) {
                if (commodity.getSpecialPrice() <= purse.getBalance()) {
                    if(!payInfo.getPassword().equals(purse.getPayPwd())){
                        throw new ServiceException("密码错误！");
                    }
                    payByBalance(purse,commodity.getSpecialPrice(),4);
                    return null;
                }else{
                    if(!payInfo.getPassword().equals(purse.getPayPwd())){
                        throw new ServiceException("密码错误！");
                    }
                    //扣除所有余额
                    int wechatPayValue = commodity.getSpecialPrice() - purse.getBalance();
                    payByBalance(purse,purse.getBalance(),4);
                    //调用微信支付
                    PayVo payVo = new PayVo();
                    payVo.setOpenId(OpenId);
                    payVo.setPayAmount(BigDecimal.valueOf(wechatPayValue));




                }
            }else{
                if (commodity.getPrice() <= purse.getBalance()) {
                    if(!payInfo.getPassword().equals(purse.getPayPwd())){
                        throw new ServiceException("密码错误！");
                    }
                    payByBalance(purse,commodity.getPrice(),4);
                    return null;
                }else{



                    //wechat pay to be implemented



                }
            }
        }else if (payInfo.getPaymentType() == 2){


            //wechat pay to be implemented
        }
        return null;
    }

    @Transactional
    private void payByBalance(UserPurse purse,int payAmount,int payType){
        purse.setBalance(purse.getBalance() - payAmount);
        purseMapper.updateBalanceByPid(purse);
        UserSpentLog log = new UserSpentLog();
        log.setAmount(0 - payAmount);
        log.setBusinessType(payType);
        log.setPid(purse.getPid());
        spentMapper.insertTransactionRecord(log);
    }

    @Transactional
    private Result payByWechatPay(int amount){
        return null;
    }


}
