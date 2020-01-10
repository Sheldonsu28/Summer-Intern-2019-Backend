package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModelProperty;

public class OrderParamDto {


	@ApiModelProperty("商家ID")
	private Integer shopId;
	
	
	@ApiModelProperty("总价")
	private Integer totalPrice;
	

	@ApiModelProperty("备注")
	private String remark;


	public Integer getShopId() {
		return shopId;
	}


	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}


	public Integer getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
