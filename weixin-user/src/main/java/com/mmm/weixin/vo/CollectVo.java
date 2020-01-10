package com.mmm.weixin.vo;

import java.math.BigDecimal;
import java.util.List;

public class CollectVo {

	private int commodityId;
	private int shopId;
	private BigDecimal price;
	private String shopName;
	private String shopLogo;
	private List<String> commodityImg;

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public List<String> getCommodityImg() {
		return commodityImg;
	}

	public void setCommodityImg(List<String> commodityImg) {
		this.commodityImg = commodityImg;
	}

}
