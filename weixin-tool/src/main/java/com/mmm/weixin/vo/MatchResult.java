package com.mmm.weixin.vo;

import java.util.Date;

public class MatchResult {
    private Integer rid;

    private Integer mid;

    private Integer hid;

    private Integer userId;

    private Integer result;

    private Date created;

    private Date updated;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
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

	@Override
	public String toString() {
		return "MatchResult [rid=" + rid + ", mid=" + mid + ", hid=" + hid + ", userId=" + userId + ", result=" + result
				+ ", created=" + created + ", updated=" + updated + "]";
	}
    
    
}