package com.mmm.weixin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GolfOrderParamDto{

	@ApiModelProperty("商家ID")
	private Integer shopId;
	
	@ApiModelProperty("商品ID")
	private Integer commodityId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty("打球日期")
	private Date playDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty("打球时间")
	private Date playtime;

	@ApiModelProperty("单价")
	private Integer unitPrice;

	@ApiModelProperty("备注")
	private String remark;

	@ApiModelProperty("联系人姓名")
	private String contactName;
	
	@ApiModelProperty("联系电话")
	private String mobile;

	@ApiModelProperty("打球人数")
	private Integer playerNum;
	
	@ApiModelProperty("打球人姓名")
	private String[] playerName;

	@ApiModelProperty("总价")
	private Integer totalPrice;

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}

	public Date getPlaytime() {
		return playtime;
	}

	public void setPlaytime(Date playtime) {
		this.playtime = playtime;
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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(Integer playerNum) {
		this.playerNum = playerNum;
	}

	public String[] getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String[] playerName) {
		this.playerName = playerName;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	
}
