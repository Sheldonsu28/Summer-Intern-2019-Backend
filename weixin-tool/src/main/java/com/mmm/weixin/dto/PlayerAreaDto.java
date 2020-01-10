package com.mmm.weixin.dto;

import java.util.List;

public class PlayerAreaDto {

	private Integer aid;
	private List<PlayerHoleDto> holes;
	private Integer resultAllAreaBar;
	
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public List<PlayerHoleDto> getHoles() {
		return holes;
	}
	public void setHoles(List<PlayerHoleDto> holes) {
		this.holes = holes;
	}
	public Integer getResultAllAreaBar() {
		return resultAllAreaBar;
	}
	public void setResultAllAreaBar(Integer resultAllAreaBar) {
		this.resultAllAreaBar = resultAllAreaBar;
	}
	
	
}
