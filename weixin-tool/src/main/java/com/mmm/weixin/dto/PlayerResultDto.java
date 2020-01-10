package com.mmm.weixin.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PlayerResultDto {

	private Integer userId;
	private String userName;
	@ApiModelProperty("头像地址")
    private String headurl;
	private Integer resultAllDifference;
	private Integer resultAllBar;
	private List<PlayerAreaDto> areas;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadurl() {
		return headurl;
	}
	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}
	public Integer getResultAllDifference() {
		return resultAllDifference;
	}
	public void setResultAllDifference(Integer resultAllDifference) {
		this.resultAllDifference = resultAllDifference;
	}
	public Integer getResultAllBar() {
		return resultAllBar;
	}
	public void setResultAllBar(Integer resultAllBar) {
		this.resultAllBar = resultAllBar;
	}
	public List<PlayerAreaDto> getAreas() {
		return areas;
	}
	public void setAreas(List<PlayerAreaDto> areas) {
		this.areas = areas;
	}
	
}
