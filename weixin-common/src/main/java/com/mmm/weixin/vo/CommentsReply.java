package com.mmm.weixin.vo;

import java.util.Date;

public class CommentsReply {
    private Integer replyId;

    private String contents;

    private Integer userId;

    private Integer touserId;

    private Integer commentId;

    private Date appendTime;


    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
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