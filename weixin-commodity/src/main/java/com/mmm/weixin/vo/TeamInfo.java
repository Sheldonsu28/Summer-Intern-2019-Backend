package com.mmm.weixin.vo;

import java.util.Date;
import java.util.List;

import com.mmm.weixin.dto.TeamMemberDto;

public class TeamInfo {
    private Integer teamId;

    private String teamName;

    private String teamContactName;

    private String teamContactPhone;

    private Date appendTime;

    private Date lastTime;

    private Integer userId;

    private Integer areaId;

    private String teamRemark;
    

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
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getTeamContactName() {
        return teamContactName;
    }

    public void setTeamContactName(String teamContactName) {
        this.teamContactName = teamContactName == null ? null : teamContactName.trim();
    }

    public String getTeamContactPhone() {
        return teamContactPhone;
    }

    public void setTeamContactPhone(String teamContactPhone) {
        this.teamContactPhone = teamContactPhone == null ? null : teamContactPhone.trim();
    }

    public Date getAppendTime() {
        return appendTime;
    }

    public void setAppendTime(Date appendTime) {
        this.appendTime = appendTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
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

    public String getTeamRemark() {
        return teamRemark;
    }

    public void setTeamRemark(String teamRemark) {
        this.teamRemark = teamRemark == null ? null : teamRemark.trim();
    }
}