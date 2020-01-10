package com.mmm.weixin.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class AppointmentDto {

	private Integer discussId;
	private Integer userId;
	private String headUrl;
	private String userName;
    private String content;
    private String created;
    private Integer sex;
    private List<AppointmentImagesDto> images;
    private List<AppointmentCommentsDto> commentsAndReplies;
    private Integer shopId;
    private String shopName;
    private Integer commentsNum;
    private Integer shareCount;
    private List<String> tags;
    private String beginDate;
    private Set<UserInfoDto> joiners;
    private Integer joinStatus;
    private String price;
    private Integer number;
    private String title;
    private String deadLine;
    private Boolean isExpired;
    
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getDiscussId() {
		return discussId;
	}
	public void setDiscussId(Integer discussId) {
		this.discussId = discussId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public List<AppointmentImagesDto> getImages() {
		return images;
	}
	public void setImages(List<AppointmentImagesDto> images) {
		this.images = images;
	}
	public List<AppointmentCommentsDto> getCommentsAndReplies() {
		return commentsAndReplies;
	}
	public void setCommentsAndReplies(List<AppointmentCommentsDto> commentsAndReplies) {
		this.commentsAndReplies = commentsAndReplies;
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
	public Integer getCommentsNum() {
		return commentsNum;
	}
	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}
	public Integer getShareCount() {
		return shareCount;
	}
	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public Set<UserInfoDto> getJoiners() {
		return joiners;
	}
	public void setJoiners(Set<UserInfoDto> joiners) {
		this.joiners = joiners;
	}
	public Integer getJoinStatus() {
		return joinStatus;
	}
	public void setJoinStatus(Integer joinStatus) {
		this.joinStatus = joinStatus;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public Boolean getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}
	
}
