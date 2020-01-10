package com.mmm.weixin.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Commodity {

    @ApiModelProperty("商品ID")
    private Integer commodityId;
    @ApiModelProperty("商品名称")
    private String commodityName;
    @ApiModelProperty("商品短标题介绍")
    private String shortTitle;
    @ApiModelProperty("添加时间")
    private Date appendTime;
    @ApiModelProperty("最后变更时间")
    private Date lastTime;
    @ApiModelProperty("所属商家ID")
    private Integer shopId;
    @ApiModelProperty("商品编码")
    private String commodityCode;
    @ApiModelProperty("商品状态(1.销售中 2.已下架)")
    private Integer stateCode;
    @ApiModelProperty("属性码(1.新品推荐 2.热门推荐)")
    private Integer propertyCode;
    @ApiModelProperty("价格")
    private Long price;
    @ApiModelProperty("特价")
    private Long specialPrice;
    @ApiModelProperty("是否为默认商品")
    private Boolean isDefault;
    @ApiModelProperty("商品类型(1.高尔夫球场商品2.旅游类套餐商品3.平台直营旅游类商品)")
    private Integer typeCode;
    @ApiModelProperty("商品详情")
    private String contents;
    @ApiModelProperty("备注文本(暂定用来存储购买须知信息)")
    private String notesText;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle == null ? null : shortTitle.trim();
    }

    public Date getAppendTime() {
        return appendTime;
    }

    public void setAppendTime(Date appendTime) {
        this.appendTime = appendTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode == null ? null : commodityCode.trim();
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public Integer getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(Integer propertyCode) {
        this.propertyCode = propertyCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Long specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public String getNotesText() {
        return notesText;
    }

    public void setNotesText(String notesText) {
        this.notesText = notesText == null ? null : notesText.trim();
    }
}