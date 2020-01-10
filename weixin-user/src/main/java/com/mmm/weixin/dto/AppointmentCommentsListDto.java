package com.mmm.weixin.dto;

import java.util.List;

public class AppointmentCommentsListDto {

	private List<AppointmentCommentsDto> commentsAndReplies;
	
	private Integer commentsNum;

	public List<AppointmentCommentsDto> getCommentsAndReplies() {
		return commentsAndReplies;
	}

	public void setCommentsAndReplies(List<AppointmentCommentsDto> commentsAndReplies) {
		this.commentsAndReplies = commentsAndReplies;
	}

	public Integer getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}
	
	
}
