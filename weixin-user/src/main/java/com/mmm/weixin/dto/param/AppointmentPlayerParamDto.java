package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class AppointmentPlayerParamDto {

	private Integer appointmentPlayerId;
	@ApiModelProperty("0拒绝 1同意")
	private Boolean isAgree;
	
	public Integer getAppointmentPlayerId() {
		return appointmentPlayerId;
	}

	public void setAppointmentPlayerId(Integer appointmentPlayerId) {
		this.appointmentPlayerId = appointmentPlayerId;
	}

	public Boolean getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(Boolean isAgree) {
		this.isAgree = isAgree;
	}

	
	
}
