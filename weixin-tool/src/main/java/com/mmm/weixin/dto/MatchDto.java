package com.mmm.weixin.dto;


public class MatchDto {

    private Integer mid;

    private Integer contestantNumber;
    
    private Integer status;
    
    private Boolean isQuit;
    
    private Integer shopId;
    
    private String name;
    
    private String areaName;
    
    private String time;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getContestantNumber() {
		return contestantNumber;
	}

	public void setContestantNumber(Integer contestantNumber) {
		this.contestantNumber = contestantNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Boolean getIsQuit() {
		return isQuit;
	}

	public void setIsQuit(Boolean isQuit) {
		this.isQuit = isQuit;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

    

}
