package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@ApiModel
public class MatchDetailDto {
	@ApiModelProperty("比赛ID")
	private Integer mid;
	@ApiModelProperty("比赛名称")
	private String matchName;
	@ApiModelProperty("比赛创建者ID")
	private int creatorId;
	private String areaName;
	private String time;
	@ApiModelProperty("是否有球童:0无，1有")
	private Integer hasCaddie;
	@ApiModelProperty("比赛状态")
	private Integer status;
	@ApiModelProperty("比赛区域")
	private AllMatchAreaDto allMatchArea;
    @ApiModelProperty("所有玩家结果")
    private List<PlayerResultDto> allPlayerResult;
    @ApiModelProperty("球童信息")
    private CaddieDto caddie;
    @ApiModelProperty("当前查询详情用户是否为球童")
    private Boolean isCaddie;

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public void setCaddie(Boolean caddie) {
		isCaddie = caddie;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}
	
	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public AllMatchAreaDto getAllMatchArea() {
		return allMatchArea;
	}

	public void setAllMatchArea(AllMatchAreaDto allMatchArea) {
		this.allMatchArea = allMatchArea;
	}

	public List<PlayerResultDto> getAllPlayerResult() {
		return allPlayerResult;
	}

	public void setAllPlayerResult(List<PlayerResultDto> allPlayerResult) {
		this.allPlayerResult = allPlayerResult;
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

	public Integer getHasCaddie() {
		return hasCaddie;
	}

	public void setHasCaddie(Integer hasCaddie) {
		this.hasCaddie = hasCaddie;
	}

	public CaddieDto getCaddie() {
		return caddie;
	}

	public void setCaddie(CaddieDto caddie) {
		this.caddie = caddie;
	}

	public Boolean getIsCaddie() {
		return isCaddie;
	}

	public void setIsCaddie(Boolean isCaddie) {
		this.isCaddie = isCaddie;
	}

    
}
