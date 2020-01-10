package com.mmm.weixin.vo;

import java.util.Date;

public class AppointmentPlayer {
    private Integer apid;

    private Integer aid;

    private Integer organizer;

    private Integer joiner;

    private Integer status;

    private Date created;

    private Date updated;

    public Integer getApid() {
        return apid;
    }

    public void setApid(Integer apid) {
        this.apid = apid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Integer organizer) {
        this.organizer = organizer;
    }

    public Integer getJoiner() {
        return joiner;
    }

    public void setJoiner(Integer joiner) {
        this.joiner = joiner;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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