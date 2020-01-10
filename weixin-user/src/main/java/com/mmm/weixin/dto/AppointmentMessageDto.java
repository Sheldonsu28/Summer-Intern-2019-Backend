package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModelProperty;

public class AppointmentMessageDto {

	private Integer amid;
	private Integer appointmentId;
	private Boolean status;
	private Integer uid;
	private Integer type;
	private String name;
	private String headUrl;
	private String content;
	private String appointmentImage;
	private String publishTime;
	@ApiModelProperty("约球状态 1申请中 2同意申请 3拒绝申请")
	private Integer applyStatus;
	@ApiModelProperty("约球申请ID")
	private Integer appointmentPlayerId;
	private String title;
	private String beginDate;
	private String deadLine;
	
	
	public Integer getAmid() {
		return amid;
	}
	public void setAmid(Integer amid) {
		this.amid = amid;
	}
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAppointmentImage() {
		return appointmentImage;
	}
	public void setAppointmentImage(String appointmentImage) {
		this.appointmentImage = appointmentImage;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	public Integer getAppointmentPlayerId() {
		return appointmentPlayerId;
	}
	public void setAppointmentPlayerId(Integer appointmentPlayerId) {
		this.appointmentPlayerId = appointmentPlayerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	
	
}
