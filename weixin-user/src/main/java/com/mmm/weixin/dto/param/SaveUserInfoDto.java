package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class SaveUserInfoDto {
    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("头像链接，非必传")
    private String headUrl = "";
    @ApiModelProperty("用户性别")
    private Integer sex;

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return sex;
    }

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
    
    
    
}
