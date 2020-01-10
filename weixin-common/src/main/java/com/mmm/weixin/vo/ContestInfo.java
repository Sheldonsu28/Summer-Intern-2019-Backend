package com.mmm.weixin.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ContestInfo {
    private Integer contestId;

    private String title;

    private String address;

    private Integer areaId;

    private Date contestBeginTime;

    private Date contestEndTime;

    private Date signUpEndTime;

    private BigDecimal price;

    private BigDecimal specialPrice;

    private Date appendTime;

    private Date lastTime;

    private Integer shopId;

    private Integer propertyCode;

    private String imgUrl;

    private Boolean stateCode;

    private BigDecimal depositAmount;

    private String contents;

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
        this.title = title == null ? null : title.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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

    public Date getSignUpEndTime() {
        return signUpEndTime;
    }

    public void setSignUpEndTime(Date signUpEndTime) {
        this.signUpEndTime = signUpEndTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Date getAppendTime() {
        return appendTime;
    }

    public void setAppendTime(Date appendTime) {
        this.appendTime = appendTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(Integer propertyCode) {
        this.propertyCode = propertyCode;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Boolean getStateCode() {
        return stateCode;
    }

    public void setStateCode(Boolean stateCode) {
        this.stateCode = stateCode;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }
}