package com.mmm.weixin.dto;

import java.util.List;

public class GolfDetailDto {

	//商家详情
	private ShopDetailDto shopDetail;
	//商品详情
	private List<CommodityDto> commodities;
	
	private Boolean isCollect;
	
	public ShopDetailDto getShopDetail() {
		return shopDetail;
	}
	public void setShopDetail(ShopDetailDto shopDetail) {
		this.shopDetail = shopDetail;
	}
	public List<CommodityDto> getCommodities() {
		return commodities;
	}
	public void setCommodities(List<CommodityDto> commodities) {
		this.commodities = commodities;
	}
	public Boolean getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Boolean isCollect) {
		this.isCollect = isCollect;
	}
	
}
