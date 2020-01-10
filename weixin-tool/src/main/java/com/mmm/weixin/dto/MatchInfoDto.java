package com.mmm.weixin.dto;

import com.mmm.weixin.vo.Match;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
@ApiModel
public class MatchInfoDto {
    @ApiModelProperty("玩家信息")
    private List<UserRoleDto> userIdsAndRoles;
    @ApiModelProperty("比赛ID，创建比赛时不用填写")
    private int matchId;
    @ApiModelProperty("比赛名称")
    private String matchName;
    @ApiModelProperty("比赛开始时间")
    private String startTime;
    @ApiModelProperty("场地ID")
    private List<Integer> areaId;
    @ApiModelProperty("商家ID")
    private int shopId;
    @ApiModelProperty("比赛允许人数")
    private int contestantNumber;

    public int getContestantNumber() {
        return contestantNumber;
    }

    public void setContestantNumber(int contestantNumber) {
        this.contestantNumber = contestantNumber;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getShopId() {
        return shopId;
    }

    public List<Integer> getAreaId() {
        return areaId;
    }

    public void setAreaId(List<Integer> areaId) {
        this.areaId = areaId;
    }

    public List<UserRoleDto> getUserIdsAndRoles() {
        return userIdsAndRoles;
    }

    public void setUserIdsAndRoles(List<UserRoleDto> playerId) {
        this.userIdsAndRoles = playerId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getMatchName() {
        return matchName;
    }


}
