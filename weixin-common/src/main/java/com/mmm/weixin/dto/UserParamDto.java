package com.mmm.weixin.dto;


import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserParamDto {
	
	@ApiModelProperty("昵称")
    private String nickName;

	@ApiModelProperty("头像")
    private String headUrl;

	@ApiModelProperty("性别")
    private Integer sex;

	@ApiModelProperty("地址")
    private String addressInfo;

	@ApiModelProperty("登录code")
	private String code;
	
	@ApiModelProperty(hidden = true)
    private String openId;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
    
    
    
}
