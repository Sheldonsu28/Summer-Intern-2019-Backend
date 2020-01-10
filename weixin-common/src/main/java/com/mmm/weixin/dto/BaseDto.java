package com.mmm.weixin.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseDto {

	@NotNull(message="当前页不能为空")
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	@NotNull(message="每页数量不能为空")
	@ApiModelProperty("每页数量")
	private Integer pageSize;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
