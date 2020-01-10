package com.mmm.weixin.dto;


public class AppointmentCommentsReplyDto {
    private int replyId;
    private int beReplyId;
    private UserInfoDto replier;
    private UserInfoDto beReplier;
    private String replyContent;

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public UserInfoDto getReplier() {
        return replier;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setReplier(UserInfoDto replier) {
        this.replier = replier;
    }

	public int getBeReplyId() {
		return beReplyId;
	}

	public void setBeReplyId(int beReplyId) {
		this.beReplyId = beReplyId;
	}

	public UserInfoDto getBeReplier() {
		return beReplier;
	}

	public void setBeReplier(UserInfoDto beReplier) {
		this.beReplier = beReplier;
	}
    
    
}
