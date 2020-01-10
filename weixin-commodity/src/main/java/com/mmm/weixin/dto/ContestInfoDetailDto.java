package com.mmm.weixin.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ContestInfoDetailDto {

    private Integer contestId;

    private String title;

    private String address;

    private Date contestBeginTime;

    private Date contestEndTime;

    private BigDecimal price;

    private Integer shopId;
    
    private String shopName;

    private String imgUrl;
    
    private String contestBeginTimeStr;
    
    private String contestEndTimeStr;

	public Integer getContestId() {
		return contestId;
	}

	public void setContestId(Integer contestId) {
		this.contestId = contestId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getContestBeginTime() {
		return contestBeginTime;
	}

	public void setContestBeginTime(Date contestBeginTime) {
		this.contestBeginTime = contestBeginTime;
	}

	public Date getContestEndTime() {
		return contestEndTime;
	}

	public void setContestEndTime(Date contestEndTime) {
		this.contestEndTime = contestEndTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getContestBeginTimeStr() {
		return contestBeginTimeStr;
	}

	public void setContestBeginTimeStr(String contestBeginTimeStr) {
		this.contestBeginTimeStr = contestBeginTimeStr;
	}

	public String getContestEndTimeStr() {
		return contestEndTimeStr;
	}

	public void setContestEndTimeStr(String contestEndTimeStr) {
		this.contestEndTimeStr = contestEndTimeStr;
	}

	
    
}
