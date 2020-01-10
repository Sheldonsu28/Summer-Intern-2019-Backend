
package com.mmm.weixin.dto;

public class GolfCommodityDto implements Comparable<GolfCommodityDto>{

	private Integer commodityId;
	private String commodityName;
	private String shortTitle;
	private Long price;
	private Integer shopId;
	private String shopName;
	private String shopLogo;
	private String addressInfo;
	private String image;
	//用户位置和球场距离
	private Double distance;
    private Long specialPrice;
    private Integer typeCode;
    private String tagsText;
    
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
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	@Override
	public int compareTo(GolfCommodityDto o) {
		return this.getDistance().compareTo(o.getDistance());
	}
	public Long getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(Long specialPrice) {
		this.specialPrice = specialPrice;
	}
	public Integer getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	public String getTagsText() {
		return tagsText;
	}
	public void setTagsText(String tagsText) {
		this.tagsText = tagsText;
	}
	
	
}
