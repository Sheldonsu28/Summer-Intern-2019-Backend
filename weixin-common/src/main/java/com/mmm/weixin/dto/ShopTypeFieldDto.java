package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopTypeFieldDto {

	private Integer fieldId;

	@ApiModelProperty("建立日期")
    private String createDate;

	@ApiModelProperty("球场面积")
    private String acreageCount;

	@ApiModelProperty("球道长度")
    private String fairwayLen;
    
	@ApiModelProperty("球道种草")
    private String fairwayGrass;
    
	@ApiModelProperty("球场电话")
    private String tel;

	@ApiModelProperty("球场特色")
    private String characteristic;
    
	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAcreageCount() {
		return acreageCount;
	}

	public void setAcreageCount(String acreageCount) {
		this.acreageCount = acreageCount;
	}
	
	public String getFairwayLen() {
		return fairwayLen;
	}

	public void setFairwayLen(String fairwayLen) {
		this.fairwayLen = fairwayLen;
	}

	public String getFairwayGrass() {
		return fairwayGrass;
	}

	public void setFairwayGrass(String fairwayGrass) {
		this.fairwayGrass = fairwayGrass;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}


    
}
