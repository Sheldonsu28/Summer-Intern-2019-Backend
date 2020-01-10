package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModelProperty;

public class AppointmentListParamDto extends BaseDto{
	@ApiModelProperty("查询类型:1当前用户的约球列表 2全部约球列表 3其他用户")
	private Integer type;
	@ApiModelProperty("查询类型：1为只看男性 2为女性 3为全部")
	private Integer sex;
	@ApiModelProperty("0全部 1已约 2未约")
	private Integer joinPlayerType;
	@ApiModelProperty("其他用户ID")
	private Integer otherUser;

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getJoinPlayerType() {
		return joinPlayerType;
	}

	public void setJoinPlayerType(Integer joinPlayerType) {
		this.joinPlayerType = joinPlayerType;
	}

	public Integer getOtherUser() {
		return otherUser;
	}

	public void setOtherUser(Integer otherUser) {
		this.otherUser = otherUser;
	}
	
	
	
}
