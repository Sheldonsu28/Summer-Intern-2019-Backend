package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询用户位置信息参数")
public class AddressParamDto {

	@ApiModelProperty("纬度")
	private String lat;
	@ApiModelProperty("经度")
	private String lng;
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
}
