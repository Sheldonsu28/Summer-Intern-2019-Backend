package com.mmm.weixin.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Comments {
    private Integer commentid;

    private Integer userid;

    @ApiModelProperty("评价内容")
    private String contents;

    @ApiModelProperty("评价图片")
    private String imgurl;

    private Date appendtime;

    private byte[] isshow;

    private Integer pluscount;

    private Integer commodityid;

    private Integer shopid;

    @ApiModelProperty("订单ID")
    private Integer orderid;

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
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

    public byte[] getIsshow() {
        return isshow;
    }

    public void setIsshow(byte[] isshow) {
        this.isshow = isshow;
    }

    public Integer getPluscount() {
        return pluscount;
    }

    public void setPluscount(Integer pluscount) {
        this.pluscount = pluscount;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
}
