package com.mmm.weixin.vo;

import java.util.Date;

public class UserCharge {
    private Integer cid;

    private String pid;

    private String payCode;

    private Integer payAmount;

    private Integer payMethod;

    private Date appendTime;

    private Date payTime;

    private String payCertificate;

    private String paySerialNumber;

    private Integer payStateCode;

    private String payRemark;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode == null ? null : payCode.trim();
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Date getAppendTime() {
        return appendTime;
    }

    public void setAppendTime(Date appendTime) {
        this.appendTime = appendTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayCertificate() {
        return payCertificate;
    }

    public void setPayCertificate(String payCertificate) {
        this.payCertificate = payCertificate == null ? null : payCertificate.trim();
    }

    public String getPaySerialNumber() {
        return paySerialNumber;
    }

    public void setPaySerialNumber(String paySerialNumber) {
        this.paySerialNumber = paySerialNumber == null ? null : paySerialNumber.trim();
    }

    public Integer getPayStateCode() {
        return payStateCode;
    }

    public void setPayStateCode(Integer payStateCode) {
        this.payStateCode = payStateCode;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark == null ? null : payRemark.trim();
    }
}