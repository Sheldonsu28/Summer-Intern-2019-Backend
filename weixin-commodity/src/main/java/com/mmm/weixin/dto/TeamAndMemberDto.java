package com.mmm.weixin.dto;

import java.util.List;

public class TeamAndMemberDto {

	private Integer teamId;
	
	private String teamName;

    private String teamContactName;

    private String teamContactPhone;
	
	private List<TeamMemberDto> members;
	
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

	public List<TeamMemberDto> getMembers() {
		return members;
	}

	public void setMembers(List<TeamMemberDto> members) {
		this.members = members;
	}

}
