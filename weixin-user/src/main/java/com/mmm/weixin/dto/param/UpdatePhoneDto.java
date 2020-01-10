package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class UpdatePhoneDto {

    @ApiModelProperty("手机号码")
    private String phoneNum;

    @ApiModelProperty("手机验证码")
    private String smsCode;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
