package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserMatchParamDto {

	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	@ApiModelProperty("每页数量")
	private Integer pageSize;
	
	@ApiModelProperty("赛事状态:2进行中，3已结束")
	private Integer status;
	
	@ApiModelProperty(hidden = true)
	private Integer userId;
	
	@ApiModelProperty("赛事ID")
	private Integer mid;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}
	
	
	
}
