package com.mmm.weixin.dto;


import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("根据属性码查询商品列表")
public class FlashSaleCommodityParamDto {

    @ApiModelProperty("当前页")
    private Integer currentPage;
    @ApiModelProperty("每页纪录数")
    private Integer pageSize;
    @NotBlank(message="属性码不能为空")
    @ApiModelProperty("属性码(1.新品推荐 2.热门推荐)")
    private Integer propertyCode;
    @ApiModelProperty("商品类型")
    private Integer typeCode;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPropertyCode() {
		return propertyCode;
	}
	public void setPropertyCode(Integer propertyCode) {
		this.propertyCode = propertyCode;
	}
	public Integer getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}
	
	
    
    
}
