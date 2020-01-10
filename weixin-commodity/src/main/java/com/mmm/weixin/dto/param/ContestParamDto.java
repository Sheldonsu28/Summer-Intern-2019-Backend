package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("团队赛事参数")
public class ContestParamDto {

	@ApiModelProperty("团队赛ID")
	private Integer contestId;

	public Integer getContestId() {
		return contestId;
	}

	public void setContestId(Integer contestId) {
		this.contestId = contestId;
	}
	
	
}
