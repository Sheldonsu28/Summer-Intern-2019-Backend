package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class PaymentDto {
    private int userId;
    private int commodityId;
    @ApiModelProperty("付款方式，1为余额支付，2为微信支付")
    private int paymentType;
    private String password;



    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
