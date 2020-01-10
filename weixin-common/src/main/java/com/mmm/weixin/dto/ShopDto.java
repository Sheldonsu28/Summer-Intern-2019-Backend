package com.mmm.weixin.dto;

import java.util.Date;

import com.mmm.weixin.vo.Shop;

public class ShopDto extends Shop {

	private Integer shopId;

	private String shopName;

	private String addressInfo;

	private String shopTel;

	private String contactName;

	private String shopLogo;

	private String contents;

	private Date createTime;

	private Date lastTime;

	private Integer stateCode;

	private Integer shopType;

	private String positionData;

	private String openingHoursStart;

	private String openingHoursEnd;

	private Integer areaId;

	private String tagsText;

	private ShopTypeFieldDto shopTypeField;
	
	

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getShopTel() {
		return shopTel;
	}

	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

	public String getPositionData() {
		return positionData;
	}

	public void setPositionData(String positionData) {
		this.positionData = positionData;
	}

	public String getOpeningHoursStart() {
		return openingHoursStart;
	}

	public void setOpeningHoursStart(String openingHoursStart) {
		this.openingHoursStart = openingHoursStart;
	}

	public String getOpeningHoursEnd() {
		return openingHoursEnd;
	}

	public void setOpeningHoursEnd(String openingHoursEnd) {
		this.openingHoursEnd = openingHoursEnd;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getTagsText() {
		return tagsText;
	}

	public void setTagsText(String tagsText) {
		this.tagsText = tagsText;
	}

	public ShopTypeFieldDto getShopTypeField() {
		return shopTypeField;
	}

	public void setShopTypeField(ShopTypeFieldDto shopTypeField) {
		this.shopTypeField = shopTypeField;
	}

}
