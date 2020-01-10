package com.mmm.weixin.vo;

import java.util.Date;

public class Shop {
    private Integer shopId;

    private String shopName;

    private String shopAccount;

    private String shopPassword;

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
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopAccount() {
        return shopAccount;
    }

    public void setShopAccount(String shopAccount) {
        this.shopAccount = shopAccount == null ? null : shopAccount.trim();
    }

    public String getShopPassword() {
        return shopPassword;
    }

    public void setShopPassword(String shopPassword) {
        this.shopPassword = shopPassword == null ? null : shopPassword.trim();
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo == null ? null : addressInfo.trim();
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel == null ? null : shopTel.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo == null ? null : shopLogo.trim();
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
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
        this.positionData = positionData == null ? null : positionData.trim();
    }

    public String getOpeningHoursStart() {
        return openingHoursStart;
    }

    public void setOpeningHoursStart(String openingHoursStart) {
        this.openingHoursStart = openingHoursStart == null ? null : openingHoursStart.trim();
    }

    public String getOpeningHoursEnd() {
        return openingHoursEnd;
    }

    public void setOpeningHoursEnd(String openingHoursEnd) {
        this.openingHoursEnd = openingHoursEnd == null ? null : openingHoursEnd.trim();
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
        this.tagsText = tagsText == null ? null : tagsText.trim();
    }
}