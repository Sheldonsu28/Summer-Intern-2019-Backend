package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModelProperty;

public class MatchShopParamDto {

    @ApiModelProperty("当前页")
    private Integer currentPage;
    @ApiModelProperty("每页纪录数")
    private Integer pageSize;
	@ApiModelProperty("球场名称")
	private String shopName;
	@ApiModelProperty("区域ID")
	private Integer areaId;
	@ApiModelProperty("排序方式:1附近 2经常")
	private Integer sortType;
	@ApiModelProperty(name = "经度",required = false)
	private double lng;
	@ApiModelProperty(name="纬度",required = false)
	private double lat;
	private String authorization;
	
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
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getSortType() {
		return sortType;
	}
	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	
	
}
