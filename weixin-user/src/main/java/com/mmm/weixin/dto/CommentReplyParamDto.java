package com.mmm.weixin.dto;

import java.util.Date;

public class CommentReplyParamDto {

    private Integer replyId;

    private String contents;

    private Integer userId;

    private Integer touserId;

    private Integer commentId;

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

	public Integer getTouserId() {
		return touserId;
	}

	public void setTouserId(Integer touserId) {
		this.touserId = touserId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Date getAppendTime() {
		return appendTime;
	}

	public void setAppendTime(Date appendTime) {
		this.appendTime = appendTime;
	}
    
    
}
