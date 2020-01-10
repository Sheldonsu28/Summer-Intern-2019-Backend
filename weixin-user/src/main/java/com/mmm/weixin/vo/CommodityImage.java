package com.mmm.weixin.vo;

public class CommodityImage {
    private Integer imgid;

    private String imgurl;

    private Integer imgtype;

    private Integer commodityid;

    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Integer getImgtype() {
        return imgtype;
    }

    public void setImgtype(Integer imgtype) {
        this.imgtype = imgtype;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }
}