package com.mmm.weixin.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class OrderForm {

    @ApiModelProperty("订单ID")
    private Integer orderFormId;
    @ApiModelProperty("订单号")
    private String orderFormCode;
    @ApiModelProperty("添加时间")
    private Date appendTime;
    @ApiModelProperty("用户ID")
    private Integer userId;
    @ApiModelProperty("订单类型(1.高尔夫球场订单 2.旅游套餐订单 3.团队赛订单)")
    private Integer orderFormType;
    @ApiModelProperty("订单最后变更时间")
    private Date lastTime;
    @ApiModelProperty("订单总额")
    private BigDecimal totalAmount;
    @ApiModelProperty("应付金额")
    private BigDecimal shouldPayAmount;
    @ApiModelProperty("实付金额")
    private BigDecimal actualPayAmount;
    @ApiModelProperty("支付方式(1.微信支付)")
    private Integer payMethod;
    @ApiModelProperty("订单状态(-2全部-1.已退款 0.已取消 1.待确认 2.待付款 3.待使用 4.待评价)")
    private Integer stateCode;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("是否删除：0否1是")
    private Boolean isDelete;
    @ApiModelProperty("球场ID")
    private Integer shopId;
	public Integer getOrderFormId() {
		return orderFormId;
	}
	public void setOrderFormId(Integer orderFormId) {
		this.orderFormId = orderFormId;
	}
	public String getOrderFormCode() {
		return orderFormCode;
	}
	public void setOrderFormCode(String orderFormCode) {
		this.orderFormCode = orderFormCode;
	}
	public Date getAppendTime() {
		return appendTime;
	}
	public void setAppendTime(Date appendTime) {
		this.appendTime = appendTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getOrderFormType() {
		return orderFormType;
	}
	public void setOrderFormType(Integer orderFormType) {
		this.orderFormType = orderFormType;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getShouldPayAmount() {
		return shouldPayAmount;
	}
	public void setShouldPayAmount(BigDecimal shouldPayAmount) {
		this.shouldPayAmount = shouldPayAmount;
	}
	public BigDecimal getActualPayAmount() {
		return actualPayAmount;
	}
	public void setActualPayAmount(BigDecimal actualPayAmount) {
		this.actualPayAmount = actualPayAmount;
	}
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

    
}