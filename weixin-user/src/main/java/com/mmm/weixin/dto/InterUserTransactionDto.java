package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class InterUserTransactionDto {
    @ApiModelProperty("转账发起者用户ID")
    private int creatorId;
    @ApiModelProperty("收账用户电话号码")
    private String receiverPhoneNum;
    @ApiModelProperty("转账金额")
    private int amount;
    @ApiModelProperty("钱包密码")
    private String password;

    public int getAmount() {
        return amount;
    }

    public int getCreaterId() {
        return creatorId;
    }

    public String getReceiverPhoneNum() {
        return receiverPhoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCreaterId(int creatorId) {
        this.creatorId = creatorId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setReceiverPhoneNum(String receiverPhoneNam) {
        this.receiverPhoneNum = receiverPhoneNam;
    }
}
