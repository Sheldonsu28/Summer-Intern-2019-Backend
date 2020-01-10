package com.mmm.weixin.vo;

import java.util.Date;

public class OrderFormRefunds {
    private Integer refundsid;

    private Integer orderformid;

    private String refundscode;

    private Integer refundsamount;

    private Integer refundsmethod;

    private Date appendtime;

    private Date refundstime;

    private String refundscertificate;

    private String refundsserialnumber;

    private Integer refundsstatus;

    private String refundsremark;

    public Integer getRefundsid() {
        return refundsid;
    }

    public void setRefundsid(Integer refundsid) {
        this.refundsid = refundsid;
    }

    public Integer getOrderformid() {
        return orderformid;
    }

    public void setOrderformid(Integer orderformid) {
        this.orderformid = orderformid;
    }

    public String getRefundscode() {
        return refundscode;
    }

    public void setRefundscode(String refundscode) {
        this.refundscode = refundscode == null ? null : refundscode.trim();
    }

    public Integer getRefundsamount() {
        return refundsamount;
    }

    public void setRefundsamount(Integer refundsamount) {
        this.refundsamount = refundsamount;
    }

    public Integer getRefundsmethod() {
        return refundsmethod;
    }

    public void setRefundsmethod(Integer refundsmethod) {
        this.refundsmethod = refundsmethod;
    }

    public Date getAppendtime() {
        return appendtime;
    }

    public void setAppendtime(Date appendtime) {
        this.appendtime = appendtime;
    }

    public Date getRefundstime() {
        return refundstime;
    }

    public void setRefundstime(Date refundstime) {
        this.refundstime = refundstime;
    }

    public String getRefundscertificate() {
        return refundscertificate;
    }

    public void setRefundscertificate(String refundscertificate) {
        this.refundscertificate = refundscertificate == null ? null : refundscertificate.trim();
    }

    public String getRefundsserialnumber() {
        return refundsserialnumber;
    }

    public void setRefundsserialnumber(String refundsserialnumber) {
        this.refundsserialnumber = refundsserialnumber == null ? null : refundsserialnumber.trim();
    }

    public Integer getRefundsstatus() {
        return refundsstatus;
    }

    public void setRefundsstatus(Integer refundsstatus) {
        this.refundsstatus = refundsstatus;
    }

    public String getRefundsremark() {
        return refundsremark;
    }

    public void setRefundsremark(String refundsremark) {
        this.refundsremark = refundsremark == null ? null : refundsremark.trim();
    }
}