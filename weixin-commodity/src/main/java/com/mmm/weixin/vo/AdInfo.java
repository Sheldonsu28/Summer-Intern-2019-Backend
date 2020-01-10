package com.mmm.weixin.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AdInfo {

    @ApiModelProperty("广告ID")
    private Integer adid;
    @ApiModelProperty("链接地址")
    private String linkurl;
    @ApiModelProperty("AD标题")
    private String title;
    @ApiModelProperty("图片地址")
    private String imgurl;
    @ApiModelProperty("广告位置(1首页2高尔夫首页)")
    private Integer showlocation;
    @ApiModelProperty("添加时间")
    private Date appendtime;
    @ApiModelProperty("最后变更时间")
    private Date lasttime;

    public Integer getAdid() {
        return adid;
    }

    public void setAdid(Integer adid) {
        this.adid = adid;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getShowlocation() {
        return showlocation;
    }

    public void setShowlocation(Integer showlocation) {
        this.showlocation = showlocation;
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
