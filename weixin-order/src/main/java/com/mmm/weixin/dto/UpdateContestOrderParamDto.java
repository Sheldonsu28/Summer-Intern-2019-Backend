package com.mmm.weixin.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class UpdateContestOrderParamDto {

	@ApiModelProperty("订单ID")
	private Integer orderId;
	
	@ApiModelProperty("单价")
	private Integer unitPrice;
	
	@ApiModelProperty("参赛选手")
	private List<TeamMemberDto> members;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<TeamMemberDto> getMembers() {
		return members;
	}

	public void setMembers(List<TeamMemberDto> members) {
		this.members = members;
	}
	
	
}
