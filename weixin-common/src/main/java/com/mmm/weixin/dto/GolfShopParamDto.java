package com.mmm.weixin.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询高尔夫球场参数")
public class GolfShopParamDto {

    @ApiModelProperty("当前页")
    private Integer currentPage;
    @ApiModelProperty("每页纪录数")
    private Integer pageSize;
	@ApiModelProperty("球场名称")
	private String golfName;
	/*
	 * @ApiModelProperty("开球日期")
	 * 
	 * @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") private Date playDate;
	 * 
	 * @ApiModelProperty("开球时间") private String playTime;
	 */
	@ApiModelProperty("区域ID")
	private Integer areaId;
	@ApiModelProperty("排序方式:1推荐排序 2价格最低 3距离最近")
	private Integer sortType;
	@ApiModelProperty(name = "经度",required = false)
	private double lng;
	@ApiModelProperty(name="纬度",required = false)
	private double lat;
	
	
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
	public String getGolfName() {
		return golfName;
	}
	public void setGolfName(String golfName) {
		this.golfName = golfName;
	}

	/*
	 * public Date getPlayDate() { return playDate; } public void setPlayDate(Date
	 * playDate) { this.playDate = playDate; } public String getPlayTime() { return
	 * playTime; } public void setPlayTime(String playTime) { this.playTime =
	 * playTime; }
	 */
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
	
	
}
