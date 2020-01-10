package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class SendMsgDto {

    @ApiModelProperty("手机号码")
    private String PhoneNum;

    @ApiModelProperty("发送短信类型(1.注册2.修改3.订单通知)")
    private Integer type;

    @ApiModelProperty("订单通知所需订单号，type为3必传")
    private String OrderFormCode;

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrderFormCode() {
        return OrderFormCode;
    }

    public void setOrderFormCode(String orderFormCode) {
        OrderFormCode = orderFormCode;
    }
}
