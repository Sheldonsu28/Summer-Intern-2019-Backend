package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModelProperty;

public class AppointmentRecommendDto {

	@ApiModelProperty("约球ID")
	private Integer aid;
	@ApiModelProperty("作者ID")
    private Integer userId;
    @ApiModelProperty("作者头像")
    private String headUrl;
    @ApiModelProperty("作者名称")
    private String nickName;
    @ApiModelProperty("约球内容")
    private String content;
    @ApiModelProperty("约球主图")
    private String imageUrl;
    @ApiModelProperty("作者性别")
    private Integer sex;
    
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
    
    
}
