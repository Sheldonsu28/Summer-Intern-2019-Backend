package com.mmm.weixin.vo;

import java.math.BigDecimal;

public class CommoditySku {
    private Integer SKUId;

    private String SKUName;

    private BigDecimal SKUPrice;

    private Integer shopId;

    private Integer stockCount;

    private Integer commodityId;

    private BigDecimal specialPrice;

    public Integer getSKUId() {
        return SKUId;
    }

    public void setSKUId(Integer SKUId) {
        this.SKUId = SKUId;
    }

    public String getSKUName() {
        return SKUName;
    }

    public void setSKUName(String SKUName) {
        this.SKUName = SKUName == null ? null : SKUName.trim();
    }

    public BigDecimal getSKUPrice() {
        return SKUPrice;
    }

    public void setSKUPrice(BigDecimal SKUPrice) {
        this.SKUPrice = SKUPrice;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public BigDecimal getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }
}