package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询高尔夫详情")
public class GolfDetailParamDto {

	@ApiModelProperty("打球日期")
	private String date;
	
	@ApiModelProperty("商品ID")
	private Integer commodityId;

	@ApiModelProperty("操作类型,0:取消收藏 1:收藏")
	private Integer type;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
