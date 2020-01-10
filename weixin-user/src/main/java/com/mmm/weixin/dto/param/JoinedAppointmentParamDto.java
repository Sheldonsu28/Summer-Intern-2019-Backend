package com.mmm.weixin.dto.param;

import com.mmm.weixin.dto.BaseDto;

import io.swagger.annotations.ApiModelProperty;

public class JoinedAppointmentParamDto extends BaseDto{

	@ApiModelProperty("0全部 1已约 2未约")
	private Integer joinPlayerType;

	public Integer getJoinPlayerType() {
		return joinPlayerType;
	}

	public void setJoinPlayerType(Integer joinPlayerType) {
		this.joinPlayerType = joinPlayerType;
	}
	
	
}
