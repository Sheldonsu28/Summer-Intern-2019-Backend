package com.mmm.weixin.dto;

import java.util.Date;
import java.util.List;


public class CommentDto{

    private Integer commentid;

    private Integer userid;

    private String contents;

    private Date appendtime;

    private Integer pluscount;

    private Integer shopid;

    private Integer orderid;
	
    private String shopName;

    private Integer replyNums;
    
    private List<String> imgUrls;
    
    private List<CommentReplyDto> replyList;

    
    
    public Integer getCommentid() {
		return commentid;
	}

	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getAppendtime() {
		return appendtime;
	}

	public void setAppendtime(Date appendtime) {
		this.appendtime = appendtime;
	}

	public Integer getPluscount() {
		return pluscount;
	}

	public void setPluscount(Integer pluscount) {
		this.pluscount = pluscount;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public List<String> getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getReplyNums() {
        return replyNums;
    }

    public void setReplyNums(Integer replyNums) {
        this.replyNums = replyNums;
    }

	public List<CommentReplyDto> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<CommentReplyDto> replyList) {
		this.replyList = replyList;
	}

    
    
    
}
