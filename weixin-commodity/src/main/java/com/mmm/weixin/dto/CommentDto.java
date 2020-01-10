package com.mmm.weixin.dto;

import java.util.List;


public class CommentDto {

    private Integer commentId;

    private Integer userId;

    private String contents;

    private String imgUrl;

    private Integer plusCount;
    
    private List<CommentReplyDto> commentReplies;
    
    private List<String> imgUrls;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getPlusCount() {
		return plusCount;
	}

	public void setPlusCount(Integer plusCount) {
		this.plusCount = plusCount;
	}

	public List<CommentReplyDto> getCommentReplies() {
		return commentReplies;
	}

	public void setCommentReplies(List<CommentReplyDto> commentReplies) {
		this.commentReplies = commentReplies;
	}

	public List<String> getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}
    
    
}
