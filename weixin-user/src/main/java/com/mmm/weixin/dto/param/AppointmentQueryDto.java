package com.mmm.weixin.dto.param;

public class AppointmentQueryDto {

	private Integer userId;

	private Integer sex;
	
	private Integer joinPlayerType;
	
	private Integer isRecommend;

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return sex;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getJoinPlayerType() {
		return joinPlayerType;
	}

	public void setJoinPlayerType(Integer joinPlayerType) {
		this.joinPlayerType = joinPlayerType;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	
	
}
