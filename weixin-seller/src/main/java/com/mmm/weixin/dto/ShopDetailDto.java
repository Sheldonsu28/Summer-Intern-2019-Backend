package com.mmm.weixin.dto;

import java.util.List;

import com.mmm.weixin.vo.ShopImage;

public class ShopDetailDto {
	
	private Integer shopId;

    private String shopName;

    private String addressInfo;

    private String shopTel;

    private String contactName;

    private String shopLogo;

    private String contents;

	
	private List<ShopImage> images;
	
	
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

	public List<ShopImage> getImages() {
		return images;
	}

	public void setImages(List<ShopImage> images) {
		this.images = images;
	}

}
