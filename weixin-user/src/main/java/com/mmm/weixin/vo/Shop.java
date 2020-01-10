package com.mmm.weixin.vo;

import java.util.Date;

public class Shop {
    private Integer shopid;

    private String shopname;

    private String shopaccount;

    private String shoppassword;

    private String addressinfo;

    private String shoptel;

    private String contactname;

    private String shoplogo;

    private String contents;

    private Date createtime;

    private Date lasttime;

    private Integer statecode;

    private Integer shoptype;

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    public String getShopaccount() {
        return shopaccount;
    }

    public void setShopaccount(String shopaccount) {
        this.shopaccount = shopaccount == null ? null : shopaccount.trim();
    }

    public String getShoppassword() {
        return shoppassword;
    }

    public void setShoppassword(String shoppassword) {
        this.shoppassword = shoppassword == null ? null : shoppassword.trim();
    }

    public String getAddressinfo() {
        return addressinfo;
    }

    public void setAddressinfo(String addressinfo) {
        this.addressinfo = addressinfo == null ? null : addressinfo.trim();
    }

    public String getShoptel() {
        return shoptel;
    }

    public void setShoptel(String shoptel) {
        this.shoptel = shoptel == null ? null : shoptel.trim();
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname == null ? null : contactname.trim();
    }

    public String getShoplogo() {
        return shoplogo;
    }

    public void setShoplogo(String shoplogo) {
        this.shoplogo = shoplogo == null ? null : shoplogo.trim();
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Integer getStatecode() {
        return statecode;
    }

    public void setStatecode(Integer statecode) {
        this.statecode = statecode;
    }

    public Integer getShoptype() {
        return shoptype;
    }

    public void setShoptype(Integer shoptype) {
        this.shoptype = shoptype;
    }
}