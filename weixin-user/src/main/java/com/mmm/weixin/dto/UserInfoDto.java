package com.mmm.weixin.dto;

import java.util.List;

public class UserInfoDto {
    private int userID;
    private String nickName;
    private String thumbNailUrl;
    private List<String> tags;
    private Integer sex;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public String getNickName() {
        return nickName;
    }

    public String getThumbNailUrl() {
        return thumbNailUrl;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setThumbNailUrl(String thumbNailUrl) {
        this.thumbNailUrl = thumbNailUrl;
    }

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
    
	
    
}
