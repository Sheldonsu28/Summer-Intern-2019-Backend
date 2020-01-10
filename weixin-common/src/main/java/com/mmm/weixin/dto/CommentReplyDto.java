package com.mmm.weixin.dto;

import com.mmm.weixin.vo.CommentsReply;

public class CommentReplyDto extends CommentsReply{

	private String fromUserName;
	private String toUserName;
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	
}
