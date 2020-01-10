package com.mmm.weixin.vo;

import java.util.Date;

public class AreaHole {
    private Integer hid;

    private Integer aid;

    private String name;

    private Integer standardBar;

    private Date created;

    private Date updated;

    private Boolean active;

    private Integer shopId;

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStandardBar() {
        return standardBar;
    }

    public void setStandardBar(Integer standardBar) {
        this.standardBar = standardBar;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}