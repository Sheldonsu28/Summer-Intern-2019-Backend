package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class OrderDto {

    @ApiModelProperty("订单ID")
    private Integer orderId;
    @ApiModelProperty("订单号")
    private String orderFormCode;
    @ApiModelProperty("订单类型(1.高尔夫球场订单 2.旅游套餐订单 3.团队赛订单)")
    private Integer orderType;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderFormCode() {
        return orderFormCode;
    }

    public void setOrderFormCode(String orderFormCode) {
        this.orderFormCode = orderFormCode;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
