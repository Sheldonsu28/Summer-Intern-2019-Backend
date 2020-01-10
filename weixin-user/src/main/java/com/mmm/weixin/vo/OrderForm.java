package com.mmm.weixin.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class OrderForm {
    private Integer orderformid;

    private String orderformcode;

    private Date appendtime;

    private Integer userid;

    private Integer orderformtype;

    private Date lasttime;

    private BigDecimal totalamount;

    private BigDecimal shouldpayamount;

    private BigDecimal actualpayamount;

    private Integer paymethod;

    @ApiModelProperty("订单状态(-2全部-1.已退款 0.已取消 1.待确认 2.待付款 3.待使用 4.待评价)")
    private Integer statecode;

    private String remark;

    private Boolean isdelete;

    private Integer shopid;

    public Integer getOrderformid() {
        return orderformid;
    }

    public void setOrderformid(Integer orderformid) {
        this.orderformid = orderformid;
    }

    public String getOrderformcode() {
        return orderformcode;
    }

    public void setOrderformcode(String orderformcode) {
        this.orderformcode = orderformcode == null ? null : orderformcode.trim();
    }

    public Date getAppendtime() {
        return appendtime;
    }

    public void setAppendtime(Date appendtime) {
        this.appendtime = appendtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getOrderformtype() {
        return orderformtype;
    }

    public void setOrderformtype(Integer orderformtype) {
        this.orderformtype = orderformtype;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public BigDecimal getShouldpayamount() {
        return shouldpayamount;
    }

    public void setShouldpayamount(BigDecimal shouldpayamount) {
        this.shouldpayamount = shouldpayamount;
    }

    public BigDecimal getActualpayamount() {
        return actualpayamount;
    }

    public void setActualpayamount(BigDecimal actualpayamount) {
        this.actualpayamount = actualpayamount;
    }

    public Integer getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(Integer paymethod) {
        this.paymethod = paymethod;
    }

    public Integer getStatecode() {
        return statecode;
    }

    public void setStatecode(Integer statecode) {
        this.statecode = statecode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }
}