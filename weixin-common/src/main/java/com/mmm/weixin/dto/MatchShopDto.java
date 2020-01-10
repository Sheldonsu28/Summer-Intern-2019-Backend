package com.mmm.weixin.dto;

import java.util.Comparator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MatchShopDto implements Comparable<MatchShopDto>{
	private Integer shopId;

    private String shopName;
    
    @ApiModelProperty("地址信息")
    private String addressInfo;
    
    @ApiModelProperty(hidden = true)
    private String positionData;
    
	//用户位置和球场距离
    @ApiModelProperty("用户距球场的距离")
	private Double distance;
	
	@ApiModelProperty("球场使用次数")
	private Integer playedTimes;
	

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getPositionData() {
		return positionData;
	}

	public void setPositionData(String positionData) {
		this.positionData = positionData;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getPlayedTimes() {
		return playedTimes;
	}

	public void setPlayedTimes(Integer playedTimes) {
		this.playedTimes = playedTimes;
	}

	@Override
	public int compareTo(MatchShopDto o) {
		// TODO Auto-generated method stub
		return this.getDistance().compareTo(o.getDistance());
	}
	
	
}
