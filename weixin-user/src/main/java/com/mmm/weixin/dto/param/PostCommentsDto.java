package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class PostCommentsDto {
    @ApiModelProperty("约球圈帖子ID或评论ID")
    private Integer discussId;
    @ApiModelProperty("被回复者ID，在加入评论时不用填")
    private Integer receiverId;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("评论类型：1回复约球圈 2回复评论 3回复评论的回复")
    private Integer type;
	public Integer getDiscussId() {
		return discussId;
	}
	public void setDiscussId(Integer discussId) {
		this.discussId = discussId;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
    
    

}
