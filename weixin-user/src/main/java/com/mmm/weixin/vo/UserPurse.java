package com.mmm.weixin.vo;

import java.util.Date;

public class UserPurse {
    private Integer pid;

    private Integer uid;

    private Integer frozeAmount;

    private Integer balance;

    private String payPwd;

    private Date created;

    private Date updated;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFrozeAmount() {
        return frozeAmount;
    }

    public void setFrozeAmount(Integer frozeAmount) {
        this.frozeAmount = frozeAmount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd == null ? null : payPwd.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}