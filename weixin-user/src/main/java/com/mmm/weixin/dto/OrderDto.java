package com.mmm.weixin.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDto {

    private Integer orderformid;

    private String orderformcode;

    private Date appendtime;

    private Integer userid;

    private Integer orderformtype;

    private Date lasttime;

    private BigDecimal totalamount;

	public Integer getOrderformid() {
		return orderformid;
	}

	public void setOrderformid(Integer orderformid) {
		this.orderformid = orderformid;
	}

	public String getOrderformcode() {
		return orderformcode;
	}

	public void setOrderformcode(String orderformcode) {
		this.orderformcode = orderformcode;
	}

	public Date getAppendtime() {
		return appendtime;
	}

	public void setAppendtime(Date appendtime) {
		this.appendtime = appendtime;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getOrderformtype() {
		return orderformtype;
	}

	public void setOrderformtype(Integer orderformtype) {
		this.orderformtype = orderformtype;
	}

	public Date getLasttime() {
		return lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public BigDecimal getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(BigDecimal totalamount) {
		this.totalamount = totalamount;
	}
    
    
}
