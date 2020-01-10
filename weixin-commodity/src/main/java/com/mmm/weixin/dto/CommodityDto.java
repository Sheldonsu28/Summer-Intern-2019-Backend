package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModelProperty;

public class CommodityDto {

	private Integer commodityId;

    private String commodityName;
    
    private String shortTitle;
    
    private Integer shopId;
    
    private Long price;

    private Long specialPrice;

    private String notesText;
    
    private Boolean isSpecial;
    
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

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
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

	public String getNotesText() {
		return notesText;
	}

	public void setNotesText(String notesText) {
		this.notesText = notesText;
	}

	public Boolean getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(Boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
    
}
