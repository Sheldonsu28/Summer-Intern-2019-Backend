package com.mmm.weixin.dto.param;

public class CommentAndReplyId {
    private Integer Id;
    //1：删除约球圈 2：删除评论 3：删除回复
    private Integer type;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
    
}
