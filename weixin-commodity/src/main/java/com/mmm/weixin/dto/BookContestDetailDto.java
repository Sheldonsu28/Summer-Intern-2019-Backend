package com.mmm.weixin.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("预定详情")
public class BookContestDetailDto extends ContestInfoDetailDto{

	@ApiModelProperty("预定须知")
	private String contents;
	
	private BigDecimal depositAmount;

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	
	
}
