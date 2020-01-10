package com.mmm.weixin.dto;

import java.util.Date;

public class CommentReplyDto {

    private Integer replyId;

    private String contents;

    private Integer userId;

    private Integer toUserId;

    private Date appendTime;

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public Date getAppendTime() {
		return appendTime;
	}

	public void setAppendTime(Date appendTime) {
		this.appendTime = appendTime;
	}
    
    
}
