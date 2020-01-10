package com.mmm.weixin.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class PayVo {

    @ApiModelProperty("小程序openid")
    private String openId;
    @ApiModelProperty("订单ID")
    private Integer orderFormId;
    @ApiModelProperty("订单号")
    private String orderFormCode;
    @ApiModelProperty("付款金额")
    private BigDecimal payAmount;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOrderFormCode() {
        return orderFormCode;
    }

    public void setOrderFormCode(String orderFormCode) {
        this.orderFormCode = orderFormCode;
    }

    public Integer getOrderFormId() {
        return orderFormId;
    }

    public void setOrderFormId(Integer orderFormId) {
        this.orderFormId = orderFormId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
