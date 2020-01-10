package com.mmm.weixin.dto;

public class AddValueDto {
    private int Uid;
    private int Amount;
    private int paymentType;

    public void setUid(int uid) {
        Uid = uid;
    }

    public int getUid() {
        return Uid;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        this.Amount = amount;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }
}
