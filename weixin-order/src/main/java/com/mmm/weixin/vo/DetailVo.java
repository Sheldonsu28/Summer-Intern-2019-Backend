package com.mmm.weixin.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class DetailVo extends OrderForm {

    @ApiModelProperty("球场名称")
    private String shopName;
    @ApiModelProperty("球场地址")
    private String addressInfo;
    @ApiModelProperty("球场电话")
    private String shopTel;
    @ApiModelProperty("球场Logo")
    private String shopLogo;
    @ApiModelProperty("打球日期")
    private String playDate;
    @ApiModelProperty("打球时间")
    private String playTime;
    @ApiModelProperty("打球人数")
    private Integer playCount;
    @ApiModelProperty("打球人")
    private String playPeople;
    @ApiModelProperty("打球人联系方式")
    private String playPeoplePhone;
    @ApiModelProperty("联系人")
    private String contactName;
    @ApiModelProperty("旅游套餐标题")
    private String travelTitle;
    @ApiModelProperty("单价")
    private BigDecimal price;
    @ApiModelProperty("参赛队伍名称")
    private String teamName;
    @ApiModelProperty("团队赛订金")
    private BigDecimal depositAmount;

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

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public String getPlayPeople() {
        return playPeople;
    }

    public void setPlayPeople(String playPeople) {
        this.playPeople = playPeople;
    }

    public String getPlayPeoplePhone() {
        return playPeoplePhone;
    }

    public void setPlayPeoplePhone(String playPeoplePhone) {
        this.playPeoplePhone = playPeoplePhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getTravelTitle() {
        return travelTitle;
    }

    public void setTravelTitle(String travelTitle) {
        this.travelTitle = travelTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}
    
	
    
}
