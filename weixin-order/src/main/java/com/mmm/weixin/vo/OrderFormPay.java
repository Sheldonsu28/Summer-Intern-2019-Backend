package com.mmm.weixin.vo;

import java.util.Date;

public class OrderFormPay {
    private Integer payid;

    private Integer orderformid;

    private String paycode;

    private Integer payamount;

    private Integer paymethod;

    private Date appendtime;

    private Date paytime;

    private String paycertificate;

    private String payserialnumber;

    private Integer paystatecode;

    private String payremark;

    public Integer getPayid() {
        return payid;
    }

    public void setPayid(Integer payid) {
        this.payid = payid;
    }

    public Integer getOrderformid() {
        return orderformid;
    }

    public void setOrderformid(Integer orderformid) {
        this.orderformid = orderformid;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode == null ? null : paycode.trim();
    }

    public Integer getPayamount() {
        return payamount;
    }

    public void setPayamount(Integer payamount) {
        this.payamount = payamount;
    }

    public Integer getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(Integer paymethod) {
        this.paymethod = paymethod;
    }

    public Date getAppendtime() {
        return appendtime;
    }

    public void setAppendtime(Date appendtime) {
        this.appendtime = appendtime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getPaycertificate() {
        return paycertificate;
    }

    public void setPaycertificate(String paycertificate) {
        this.paycertificate = paycertificate == null ? null : paycertificate.trim();
    }

    public String getPayserialnumber() {
        return payserialnumber;
    }

    public void setPayserialnumber(String payserialnumber) {
        this.payserialnumber = payserialnumber == null ? null : payserialnumber.trim();
    }

    public Integer getPaystatecode() {
        return paystatecode;
    }

    public void setPaystatecode(Integer paystatecode) {
        this.paystatecode = paystatecode;
    }

    public String getPayremark() {
        return payremark;
    }

    public void setPayremark(String payremark) {
        this.payremark = payremark == null ? null : payremark.trim();
    }
}