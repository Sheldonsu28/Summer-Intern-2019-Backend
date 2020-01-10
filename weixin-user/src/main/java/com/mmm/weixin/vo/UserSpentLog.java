package com.mmm.weixin.vo;

import java.util.Date;

public class UserSpentLog {
    private Integer lid;

    private Integer pid;

    private Date appendTime;

    private Integer amount;

    private Integer businessType;

    private Date created;

    private Date updated;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getAppendTime() {
        return appendTime;
    }

    public void setAppendTime(Date appendTime) {
        this.appendTime = appendTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
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