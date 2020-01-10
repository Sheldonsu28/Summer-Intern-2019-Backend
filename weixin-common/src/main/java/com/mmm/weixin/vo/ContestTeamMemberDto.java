package com.mmm.weixin.vo;

public class ContestTeamMemberDto {

	private Integer memberId;
	private String name;
	private Boolean hasJoin=false;
	
	
	public Boolean getHasJoin() {
		return hasJoin;
	}
	public void setHasJoin(Boolean hasJoin) {
		this.hasJoin = hasJoin;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
