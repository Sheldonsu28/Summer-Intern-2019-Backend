package com.mmm.weixin.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("赛事订单")
public class ContestOrderParamDto {

	@ApiModelProperty("商家ID")
	private Integer shopId;
	
	@ApiModelProperty("单价")
	private Integer unitPrice;

	@ApiModelProperty("备注")
	private String remark;

	@ApiModelProperty("球队ID")
	private Integer teamId;
	
	@ApiModelProperty("球队名称")
	private String teamName;

	@ApiModelProperty("打球人数")
	private Integer playerNum;
	
	@ApiModelProperty("联系电话")
	private String contactPhone;
	
	@ApiModelProperty("联系人")
	private String contactName;

	@ApiModelProperty("总价")
	private Integer totalPrice;
	
	@ApiModelProperty("赛事ID")
	private Integer contestId;
	
	@ApiModelProperty("参赛选手")
	private List<TeamMemberDto> members;
	
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	
	
	public Integer getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(Integer playerNum) {
		this.playerNum = playerNum;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getContestId() {
		return contestId;
	}

	public void setContestId(Integer contestId) {
		this.contestId = contestId;
	}

	public List<TeamMemberDto> getMembers() {
		return members;
	}

	public void setMembers(List<TeamMemberDto> members) {
		this.members = members;
	}


}
