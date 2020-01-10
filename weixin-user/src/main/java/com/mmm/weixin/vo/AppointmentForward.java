package com.mmm.weixin.vo;

import java.util.Date;

public class AppointmentForward {
    private Integer afid;

    private Integer aid;

    private Integer sendUserId;

    private Date created;

    private Date updated;

    public Integer getAfid() {
        return afid;
    }

    public void setAfid(Integer afid) {
        this.afid = afid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
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