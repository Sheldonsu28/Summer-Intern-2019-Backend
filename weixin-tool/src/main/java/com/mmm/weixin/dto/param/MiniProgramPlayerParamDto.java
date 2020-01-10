package com.mmm.weixin.dto.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MiniProgramPlayerParamDto {

	@ApiModelProperty("比赛ID")
	private Integer mid;
	@ApiModelProperty("类型:1选手，2球童")
	private Integer type;
	//private UserParamDto user;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
