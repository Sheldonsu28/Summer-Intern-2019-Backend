package com.mmm.weixin.vo;

public class ShopTypeFieldValue {
    private Integer valueid;

    private Integer fieldid;

    private String valuetext;

    private Integer shopid;

    public Integer getValueid() {
        return valueid;
    }

    public void setValueid(Integer valueid) {
        this.valueid = valueid;
    }

    public Integer getFieldid() {
        return fieldid;
    }

    public void setFieldid(Integer fieldid) {
        this.fieldid = fieldid;
    }

    public String getValuetext() {
        return valuetext;
    }

    public void setValuetext(String valuetext) {
        this.valuetext = valuetext == null ? null : valuetext.trim();
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }
}