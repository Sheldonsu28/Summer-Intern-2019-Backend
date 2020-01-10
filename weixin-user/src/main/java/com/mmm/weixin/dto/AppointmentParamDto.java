package com.mmm.weixin.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class AppointmentParamDto {

	private Integer shopId;
	
	@ApiModelProperty("标题")
	private String title;
	
	@ApiModelProperty("报名截止时间")
	private String deadLine;
	
	@ApiModelProperty("内容")
    private String content;
	@ApiModelProperty("费用")
    private String price;
    @ApiModelProperty("人数")
    private Integer number;
    
    private List<AppointmentImagesDto> images;
    @ApiModelProperty("开始时间")
    private String matchStartTime;

	public String getMatchStartTime() {
		return matchStartTime;
	}

	public void setMatchStartTime(String matchStartTime) {
		this.matchStartTime = matchStartTime;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public List<AppointmentImagesDto> getImages() {
		return images;
	}

	public void setImages(List<AppointmentImagesDto> images) {
		this.images = images;
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
    
}
