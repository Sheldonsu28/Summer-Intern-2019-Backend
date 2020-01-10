package com.mmm.weixin.dto;

import java.util.List;


public class AllMatchAreaDto {
	private Integer allAreasDifference;
	
	private Integer allAreasBar;
	
	private List<MatchAreaDto>  areas;

	public Integer getAllAreasDifference() {
		return allAreasDifference;
	}

	public void setAllAreasDifference(Integer allAreasDifference) {
		this.allAreasDifference = allAreasDifference;
	}

	public Integer getAllAreasBar() {
		return allAreasBar;
	}

	public void setAllAreasBar(Integer allAreasBar) {
		this.allAreasBar = allAreasBar;
	}

	public List<MatchAreaDto> getAreas() {
		return areas;
	}

	public void setAreas(List<MatchAreaDto> areas) {
		this.areas = areas;
	}
	
	
}
