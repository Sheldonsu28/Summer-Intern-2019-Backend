package com.mmm.weixin.dto.param;

import com.mmm.weixin.dto.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询团体赛参数")
public class ContestInfoQueryParamDto extends BaseDto{

	@ApiModelProperty("区域ID")
	private Integer areaId;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
}
