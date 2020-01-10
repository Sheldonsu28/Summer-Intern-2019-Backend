package com.mmm.weixin.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Feedback {

    private Integer feedbackid;

    private Integer userid;

    @ApiModelProperty("反馈内容")
    private String contents;

    @ApiModelProperty("联系方式")
    private String contactinfo;

    @ApiModelProperty("反馈图片链接")
    private String imgurl;

    private Date appendtime;

    private Date lasttime;

    public Integer getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(Integer feedbackid) {
        this.feedbackid = feedbackid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public String getContactinfo() {
        return contactinfo;
    }

    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo == null ? null : contactinfo.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
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
}