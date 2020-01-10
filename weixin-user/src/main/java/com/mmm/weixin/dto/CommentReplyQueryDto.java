package com.mmm.weixin.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CommentReplyQueryDto {
	
	@ApiModelProperty("内容")
	private String contents;

	@ApiModelProperty(hidden = true) 
    private Integer userId;

    @ApiModelProperty("被回复者的ID")
    private Integer toUserId;

    @ApiModelProperty("评价ID")
    private Integer commentId;
    

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

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

    
	
}
