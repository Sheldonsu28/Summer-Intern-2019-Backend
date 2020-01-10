package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("团赛队伍参数")
public class TeamInfoAddDto {

	private Integer teamId;
	
	private String teamName;

	private String teamContactName;

	private String teamContactPhone;

	@ApiModelProperty(hidden = true)
	private Integer userId;

	private Integer areaId;

	private String[] memberNames;

	
	
	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamContactName() {
		return teamContactName;
	}

	public void setTeamContactName(String teamContactName) {
		this.teamContactName = teamContactName;
	}

	public String getTeamContactPhone() {
		return teamContactPhone;
	}

	public void setTeamContactPhone(String teamContactPhone) {
		this.teamContactPhone = teamContactPhone;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String[] getMemberNames() {
		return memberNames;
	}

	public void setMemberNames(String[] memberNames) {
		this.memberNames = memberNames;
	}

}
