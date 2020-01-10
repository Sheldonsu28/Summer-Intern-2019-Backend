package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AreaDataParamDto {

	@ApiModelProperty(hidden=true)
	private Integer isFuzzy=0;
	
    private String areaName;

    private Boolean isHot;

    private Integer parentId;

    
	public Integer getIsFuzzy() {
		return isFuzzy;
	}

	public void setIsFuzzy(Integer isFuzzy) {
		this.isFuzzy = isFuzzy;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}
