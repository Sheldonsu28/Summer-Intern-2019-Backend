package com.mmm.weixin.dto;

import java.util.List;

public class MatchAreaDto {

	private Integer aid;
	private String areaName;
	private List<MatchHoleDto> holes;
	private Integer allHoleStandardBar;
	
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public List<MatchHoleDto> getHoles() {
		return holes;
	}
	public void setHoles(List<MatchHoleDto> holes) {
		this.holes = holes;
	}
	public Integer getAllHoleStandardBar() {
		return allHoleStandardBar;
	}
	public void setAllHoleStandardBar(Integer allHoleStandardBar) {
		this.allHoleStandardBar = allHoleStandardBar;
	}
	
	
}
