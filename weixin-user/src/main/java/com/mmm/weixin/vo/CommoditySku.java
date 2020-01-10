package com.mmm.weixin.vo;

import java.math.BigDecimal;

public class CommoditySku {
    private Integer skuid;

    private String skuname;

    private BigDecimal skuprice;

    private Integer shopid;

    private Integer stockcount;

    private Integer commodityid;

    private BigDecimal specialprice;

    public Integer getSkuid() {
        return skuid;
    }

    public void setSkuid(Integer skuid) {
        this.skuid = skuid;
    }

    public String getSkuname() {
        return skuname;
    }

    public void setSkuname(String skuname) {
        this.skuname = skuname == null ? null : skuname.trim();
    }

    public BigDecimal getSkuprice() {
        return skuprice;
    }

    public void setSkuprice(BigDecimal skuprice) {
        this.skuprice = skuprice;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getStockcount() {
        return stockcount;
    }

    public void setStockcount(Integer stockcount) {
        this.stockcount = stockcount;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public BigDecimal getSpecialprice() {
        return specialprice;
    }

    public void setSpecialprice(BigDecimal specialprice) {
        this.specialprice = specialprice;
    }
}