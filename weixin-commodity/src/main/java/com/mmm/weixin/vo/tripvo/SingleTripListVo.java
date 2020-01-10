package com.mmm.weixin.vo.tripvo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class SingleTripListVo {

    @ApiModelProperty("商品ID")
    private Integer commodityId;
    @ApiModelProperty("商品名称")
    private String commodityName;
    @ApiModelProperty("商品短标题")
    private String shortTitle;
    @ApiModelProperty("商品价格")
    private BigDecimal price;
    @ApiModelProperty("属性码(1.新品推荐 2.热门推荐)")
    private Integer propertyCode;
    @ApiModelProperty("所属商家ID")
    private Integer shopId;
    @ApiModelProperty("商品编码")
    private String commodityCode;
    @ApiModelProperty("商品状态(1.销售中 2.已下架)")
    private Integer stateCode;
    @ApiModelProperty("特价")
    private BigDecimal specialPrice;
    @ApiModelProperty("是否为默认商品")
    private Boolean isDefault;
    @ApiModelProperty("商品类型(1.高尔夫球场商品2.旅游类套餐商品3.平台直营旅游类商品)")
    private Integer typeCode;
    @ApiModelProperty("商品图片")
    private List<String> imgUrls;
    @ApiModelProperty("旅游详情图片介绍")
    private List<String> detailImgUrls;
//    @ApiModelProperty("旅游详情介绍")
//    private String contents;

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
        this.commodityName = commodityName;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(Integer propertyCode) {
        this.propertyCode = propertyCode;
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
        this.commodityCode = commodityCode;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public BigDecimal getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<String> getDetailImgUrls() {
        return detailImgUrls;
    }

    public void setDetailImgUrls(List<String> detailImgUrls) {
        this.detailImgUrls = detailImgUrls;
    }

//    public String getContents() {
//        return contents;
//    }
//
//    public void setContents(String contents) {
//        this.contents = contents;
//    }
}
