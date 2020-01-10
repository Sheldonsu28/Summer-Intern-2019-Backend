package com.mmm.weixin.vo;

import java.math.BigDecimal;

public class OrderFormItems {
    private Integer itemid;

    private Integer orderformid;

    private BigDecimal sellprice;

    private BigDecimal actualsellprice;

    private Integer sellcount;

    private BigDecimal totalamount;

    private Integer commodityid;

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getOrderformid() {
        return orderformid;
    }

    public void setOrderformid(Integer orderformid) {
        this.orderformid = orderformid;
    }

    public BigDecimal getSellprice() {
        return sellprice;
    }

    public void setSellprice(BigDecimal sellprice) {
        this.sellprice = sellprice;
    }

    public BigDecimal getActualsellprice() {
        return actualsellprice;
    }

    public void setActualsellprice(BigDecimal actualsellprice) {
        this.actualsellprice = actualsellprice;
    }

    public Integer getSellcount() {
        return sellcount;
    }

    public void setSellcount(Integer sellcount) {
        this.sellcount = sellcount;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }
}