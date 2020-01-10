package com.mmm.weixin.dto;

import java.util.List;

public class AppointmentCommentsDto {
    private int commentId;
    private  UserInfoDto commenter;
    private String commentContent;
    private String commentTime;
    private int likes;
    private Integer commentNums;
    private List<AppointmentCommentsReplyDto> replies;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public List<AppointmentCommentsReplyDto> getReplies() {
        return replies;
    }

    public void setReplies(List<AppointmentCommentsReplyDto> replies) {
        this.replies = replies;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public UserInfoDto getCommenter() {
        return commenter;
    }

    public void setCommenter(UserInfoDto commenter) {
        this.commenter = commenter;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

	public Integer getCommentNums() {
		return commentNums;
	}

	public void setCommentNums(Integer commentNums) {
		this.commentNums = commentNums;
	}
    
    
}
