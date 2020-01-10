package com.mmm.weixin.vo;

import java.util.Date;

public class Message {
    private Integer messageid;

    private Integer messagetype;

    private String contents;

    private Integer userid;

    private Date appendtime;

    private Date lasttime;

    private Boolean isread;

    private Boolean issms;

    private String smsphone;

    private String smssendresult;

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public Integer getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(Integer messagetype) {
        this.messagetype = messagetype;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getAppendtime() {
        return appendtime;
    }

    public void setAppendtime(Date appendtime) {
        this.appendtime = appendtime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Boolean getIsread() {
        return isread;
    }

    public void setIsread(Boolean isread) {
        this.isread = isread;
    }

    public Boolean getIssms() {
        return issms;
    }

    public void setIssms(Boolean issms) {
        this.issms = issms;
    }

    public String getSmsphone() {
        return smsphone;
    }

    public void setSmsphone(String smsphone) {
        this.smsphone = smsphone == null ? null : smsphone.trim();
    }

    public String getSmssendresult() {
        return smssendresult;
    }

    public void setSmssendresult(String smssendresult) {
        this.smssendresult = smssendresult == null ? null : smssendresult.trim();
    }
}