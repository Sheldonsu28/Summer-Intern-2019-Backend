package com.mmm.weixin.service;


import com.mmm.weixin.dto.AppointmentCommentsDto;
import com.mmm.weixin.dto.AppointmentCommentsListDto;
import com.mmm.weixin.dto.param.CommentAndReplyId;
import com.mmm.weixin.dto.param.PostCommentsDto;
import com.mmm.weixin.vo.AppointmentComment;
import com.mmm.weixin.vo.AppointmentCommentReply;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

public interface IAppointmentCommentService {
    public AppointmentCommentsListDto insertComment(PostCommentsDto comment,HttpServletRequest request);
    public List<AppointmentCommentsDto> getCommentsAndReplies(int appointmentId);
    public AppointmentCommentsListDto deleteComment(CommentAndReplyId commentId,HttpServletRequest request);
    public AppointmentCommentsListDto deleteReply(CommentAndReplyId replyId, HttpServletRequest request);
    public void insertMessage(Integer id,Integer type,Integer senderId,Integer receiverId);
	String calculateTime(Date date);
	public AppointmentComment getCommentById(Integer commentId);
	public AppointmentCommentReply getReplyById(Integer arid);
}
