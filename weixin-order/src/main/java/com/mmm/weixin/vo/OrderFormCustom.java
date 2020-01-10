package com.mmm.weixin.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class OrderFormCustom extends OrderForm {

    @ApiModelProperty("打球日期")
    private String playDate;
    @ApiModelProperty("打球时间")
    private String playTime;
    @ApiModelProperty("打球人数")
    private Integer playUserCount;
    @ApiModelProperty("球场名称")
    private String shopName;
    @ApiModelProperty("球场Logo")
    private String shopLogo;
    @ApiModelProperty("球场图片")
    private String[] imgUrls;
    @ApiModelProperty("团队赛订金")
    private BigDecimal depositAmount;
    
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

    public Integer getPlayUserCount() {
        return playUserCount;
    }

    public void setPlayUserCount(Integer playUserCount) {
        this.playUserCount = playUserCount;
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

    public String[] getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String[] imgUrls) {
        this.imgUrls = imgUrls;
    }

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}
    
    
}
