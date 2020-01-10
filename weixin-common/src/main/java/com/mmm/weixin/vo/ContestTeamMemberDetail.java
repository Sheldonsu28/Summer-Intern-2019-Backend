package com.mmm.weixin.vo;

import java.util.List;

public class ContestTeamMemberDetail {

	private Integer ctId;

    private Integer teamId;

    private String teamName;
    
    private Integer contestId;
    
    private List<ContestTeamMemberDto> members;

	public Integer getCtId() {
		return ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

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

	public Integer getContestId() {
		return contestId;
	}

	public void setContestId(Integer contestId) {
		this.contestId = contestId;
	}

	public List<ContestTeamMemberDto> getMembers() {
		return members;
	}

	public void setMembers(List<ContestTeamMemberDto> members) {
		this.members = members;
	}
    
    
} 
