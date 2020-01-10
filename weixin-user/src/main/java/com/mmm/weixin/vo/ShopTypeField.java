package com.mmm.weixin.vo;

public class ShopTypeField {
    private Integer fieldid;

    private String fieldenname;

    private String fieldcnname;

    private String remark;

    private String fieldtype;

    private Integer shoptype;

    public Integer getFieldid() {
        return fieldid;
    }

    public void setFieldid(Integer fieldid) {
        this.fieldid = fieldid;
    }

    public String getFieldenname() {
        return fieldenname;
    }

    public void setFieldenname(String fieldenname) {
        this.fieldenname = fieldenname == null ? null : fieldenname.trim();
    }

    public String getFieldcnname() {
        return fieldcnname;
    }

    public void setFieldcnname(String fieldcnname) {
        this.fieldcnname = fieldcnname == null ? null : fieldcnname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getFieldtype() {
        return fieldtype;
    }

    public void setFieldtype(String fieldtype) {
        this.fieldtype = fieldtype == null ? null : fieldtype.trim();
    }

    public Integer getShoptype() {
        return shoptype;
    }

    public void setShoptype(Integer shoptype) {
        this.shoptype = shoptype;
    }
}