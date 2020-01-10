package com.mmm.weixin.service.impl;

import com.mmm.weixin.common.TokenValidate;
import com.mmm.weixin.constants.UserConstants;
import com.mmm.weixin.dao.AppointmentCommentMapper;
import com.mmm.weixin.dao.AppointmentCommentReplyMapper;
import com.mmm.weixin.dao.AppointmentMapper;
import com.mmm.weixin.dao.AppointmentMessageMapper;
import com.mmm.weixin.dao.UserMapper;
import com.mmm.weixin.dto.AppointmentCommentsDto;
import com.mmm.weixin.dto.AppointmentCommentsListDto;
import com.mmm.weixin.dto.AppointmentCommentsReplyDto;
import com.mmm.weixin.dto.UserInfoDto;
import com.mmm.weixin.dto.param.CommentAndReplyId;
import com.mmm.weixin.dto.param.PostCommentsDto;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.service.IAppointmentCommentService;
import com.mmm.weixin.service.IAppointmentService;
import com.mmm.weixin.utils.base.ValidateHelper;
import com.mmm.weixin.vo.Appointment;
import com.mmm.weixin.vo.AppointmentComment;
import com.mmm.weixin.vo.AppointmentCommentReply;
import com.mmm.weixin.vo.AppointmentMessage;
import com.mmm.weixin.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Service
public class AppointmentCommentServiceImp implements IAppointmentCommentService {

    @Autowired
    private TokenValidate tokenValidate;
    @Autowired
    private AppointmentCommentMapper commentMapper;
    @Autowired
    private AppointmentCommentReplyMapper replyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AppointmentMessageMapper msgMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private IAppointmentService appointmentService;
    
    /**
     * 返回所查约球圈帖子所包含的所有评论和回复
     * @param appointmentId     约球圈帖子ID
     * @return                  所有的评论与回复
     */
    public List<AppointmentCommentsDto> getCommentsAndReplies(int appointmentId) {
        List<AppointmentCommentsDto> postComments = new ArrayList<>();
        List<AppointmentComment> comments = commentMapper.selectByAppointmentID(appointmentId);
        Iterator<AppointmentComment> commentsIterator = comments.iterator();
        AppointmentCommentsDto commentsDto = null;
        int num = 0;
        while(commentsIterator.hasNext()){
        	++num;
            AppointmentComment comment = commentsIterator.next();
            commentsDto = new AppointmentCommentsDto();
            commentsDto.setCommentId(comment.getAcid());
            commentsDto.setCommentContent(comment.getContent());
            commentsDto.setLikes(0);
            commentsDto.setCommenter(getUserInfoByID(comment.getUserId()));
            List<AppointmentCommentsReplyDto> commentReplies = getCommentReplies(comment);
            num += commentReplies.size();
            commentsDto.setReplies(commentReplies);
            commentsDto.setCommentTime(calculateTime(comment.getCreated()));
            postComments.add(commentsDto);
        }
        return postComments;
    }

    /**
     * getCommentsAndReplies的辅助方法，使用userId找出用户信息
     * @param userId 评论信息
     * @return       用户信息
     */
    private UserInfoDto getUserInfoByID(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserID(user.getUserId());
        userInfoDto.setNickName(user.getNickName());
        userInfoDto.setThumbNailUrl(user.getHeadUrl());
        userInfoDto.setSex(user.getSex());
        List<String> tags = new ArrayList<String>();
        String tagsStr = user.getTags();
        if(ValidateHelper.isNotEmptyString(tagsStr)) {
        	tags = Arrays.asList(tagsStr.split(","));
        }
        userInfoDto.setTags(tags);
        return userInfoDto;
    }

    /**
     * getCommentsAndReplies的辅助方法，使用comment类找到可以显示的评论
     * @param comment   需要查找回复的评论
     * @return          评论里的回复
     */
    private List<AppointmentCommentsReplyDto> getCommentReplies(AppointmentComment comment){
        List<AppointmentCommentReply> replies = replyMapper.selectByCommentID(comment.getAcid());
        ArrayList<AppointmentCommentsReplyDto> returnValue = new ArrayList<>();
        AppointmentComment comment1 = new AppointmentComment();
        Iterator<AppointmentCommentReply> replyIterator = replies.iterator();
        AppointmentCommentsReplyDto replyDto = null;
        //根据评论ID找到可以以显示的回复
        while(replyIterator.hasNext()){
        	replyDto = new AppointmentCommentsReplyDto();
            AppointmentCommentReply reply = replyIterator.next();
            replyDto.setReplyId(reply.getArid());
            replyDto.setReplier(getUserInfoByID(reply.getUserId()));
            if(null != reply.getReplyId()) {
            	replyDto.setBeReplier(getUserInfoByID(reply.getReplyUserId()));
            }
            replyDto.setReplyContent(reply.getContent());
            returnValue.add(replyDto);
        }
        return returnValue;
    }

    /**
     *  计算约球圈评论发布的时间离现在差了多少时间
     * @param date   评论
     * @return          评论时间与现在时间差了多少
     */
    @Override
    public String calculateTime(Date date){
        long time = System.currentTimeMillis() - date.getTime();
        if(time/60000 < 1){                                                 //判断时间不到一分钟
            return "刚刚";
        }else if(time/60000 < 60){                                          //判断时间不到一个小时一小时
            return String.format("%d分钟前",(int) Math.floor(time/60000));
        }else if(time/3600000 < 24){                                        //判断时间不到一天
            return String.format("%d小时前",(int) Math.floor(time/3600000));
        }else if(time/86400000 < 30){                                       //判断时间不到一个月
            return String.format("%d天前",(int) Math.floor(time/86400000));
        }
        return String.format("%d个月前",(int) Math.floor(time/86400000));   //没有年是因为数字太大了
    }

    /**
     * 删除评论
     * @param commentId     评论ID
     */
    @Transactional
    @Override
    public AppointmentCommentsListDto deleteComment(CommentAndReplyId commentId, HttpServletRequest request) {
    	Integer type = commentId.getType();
    	Integer id = commentId.getId();
    	int updater = getUserIdFromRequest(request);
    	if(null == type || type == 0) {
    		throw new ServiceException("删除类型不能为空");
    	}
    	if(UserConstants.APPOINTMENT_COMMENT_TYPE_APPOINTMENT==type) {
    		Appointment appointment = appointmentMapper.selectByPrimaryKey(id);
    		if(appointment.getUserId() == updater) {
    			appointment.setStatus(0);
    			appointmentMapper.updateByPrimaryKey(appointment);
    			return null;
    		}else {
    			throw new ServiceException("您无权删除该评论");
    		}
    	}else if(UserConstants.APPOINTMENT_COMMENT_TYPE_COMMENT==type) {
	        AppointmentComment comment = commentMapper.selectByPrimaryKey(id);
	        if (comment.getUserId() == updater) {
	            comment.setIsShow(false);
	            commentMapper.updateByPrimaryKey(comment);
	            int appointmentID = commentMapper.selectByPrimaryKey(commentId.getId()).getAid();
	            AppointmentCommentsListDto result = new AppointmentCommentsListDto();
	            List<AppointmentCommentsDto> commentsAndReplies = this.getCommentsAndReplies(appointmentID);
	            int commentNums = this.getCommentNums(commentsAndReplies);
	            result.setCommentsAndReplies(commentsAndReplies);
	            result.setCommentsNum(commentNums);
	            return result;
	        }else{
	            throw new ServiceException("您无权删除该评论");
	        }
        }else if(UserConstants.APPOINTMENT_COMMENT_TYPE_COMMENT_REPLY==type){
        	return this.deleteReply(commentId, request);
        }
    	return null;
    }

    /**
     * 往数据库中加入评论
     * @param comment  评论类
     */
    @Transactional
    @Override
    public  AppointmentCommentsListDto insertComment(PostCommentsDto comment, HttpServletRequest request) {
    	AppointmentCommentsListDto result = new AppointmentCommentsListDto();
    	Integer type = comment.getType();
    	//type为APPOINTMENT_COMMENT_TYPE_APPOINTMENT时，discussId表示AppointmentId，为APPOINTMENT_COMMENT_TYPE_COMMENT时，discussId表示commentId。否则为replyId
    	int discussId = comment.getDiscussId();
    	int appointmentId = 0;
    	int commentId = 0;
    	if(UserConstants.APPOINTMENT_COMMENT_TYPE_APPOINTMENT == type) {
    		appointmentId = discussId;
    		AppointmentComment appointmentComment = new AppointmentComment();
    		int uid = getUserIdFromRequest(request);
    		appointmentComment.setUserId(uid);
    		appointmentComment.setAid(discussId);
    		appointmentComment.setIsShow(true);
    		appointmentComment.setContent(comment.getContent());
    		appointmentComment.setCreated(new Date());
    		commentMapper.insert(appointmentComment);
    		Appointment appointment = appointmentMapper.selectByPrimaryKey(discussId);
    		this.insertMessage(appointmentComment.getAcid(), UserConstants.APPOINTMENT_MESSAGE_TYPE_COMMENT, uid,appointment.getUserId());
    	}else {
    		boolean isReplyToComment = UserConstants.APPOINTMENT_COMMENT_TYPE_COMMENT==type?true:false;
    		AppointmentCommentReply commentReply = new AppointmentCommentReply();
    		//type为APPOINTMENT_COMMENT_TYPE_COMMENT时，discussId表示commentId。否则为replyId
    		if(isReplyToComment) {
    			commentReply.setCommentId(discussId);
    			commentId = discussId;
    		}else {
    			AppointmentCommentReply reply = replyMapper.selectByPrimaryKey(discussId);
    			//从reply获取commentId
    			Integer replyCommentId = reply.getCommentId();
    			commentReply.setCommentId(replyCommentId);
    			commentId = replyCommentId;
    		}
    		//回复评论,replyId为空。否则不为空
    		commentReply.setReplyId(isReplyToComment?null:discussId);
    		commentReply.setContent(comment.getContent());

    		int uid = getUserIdFromRequest(request);
    		commentReply.setUserId(uid);
    		int receiverId = comment.getReceiverId();
    		commentReply.setReplyUserId(receiverId);
    		commentReply.setIsShow(true);
    		commentReply.setCreated(new Date());
    		replyMapper.insert(commentReply);
    		
    		AppointmentComment appointmentComment = commentMapper.selectByPrimaryKey(commentId);
    		appointmentId = appointmentComment.getAid();
    		Appointment appointment = appointmentMapper.selectByPrimaryKey(appointmentId);
    		Integer arid = commentReply.getArid();
    		//发表评论者ID
    		Integer commentUserId = appointmentComment.getUserId();
    		this.insertMessage(arid, UserConstants.APPOINTMENT_MESSAGE_TYPE_REPLY, uid,receiverId);
    		//如果评论者和被回复者不是同个用户，评论者将接收到消息
    		if(commentUserId != receiverId) {
    			this.insertMessage(arid, UserConstants.APPOINTMENT_MESSAGE_TYPE_REPLY,uid,commentUserId);
    		}
    		this.insertMessage(arid, UserConstants.APPOINTMENT_MESSAGE_TYPE_REPLY, uid,appointment.getUserId());
    	}
    	List<AppointmentCommentsDto> commentsAndReplies = this.getCommentsAndReplies(appointmentId);
    	int num = getCommentNums(commentsAndReplies);
    	result.setCommentsAndReplies(commentsAndReplies);
    	result.setCommentsNum(num);
    	return result;
    }


    private int getCommentNums(List<AppointmentCommentsDto> commentsAndReplies) {
    	Iterator<AppointmentCommentsDto> iterator = commentsAndReplies.iterator();
    	int num =0;
    	while(iterator.hasNext()) {
    		++num;
    		AppointmentCommentsDto comment = iterator.next();
    		List<AppointmentCommentsReplyDto> replies = comment.getReplies();
    		num+=replies.size();
    	}
    	return num;
	}

	/**
     * 从request解析用户ID
     * @param request 请求
     * @return        请求的用户ID
     */
    private int getUserIdFromRequest(HttpServletRequest request){
        String authorization = request.getHeader("authorization");
        return tokenValidate.validateToken(authorization);
    }

    /**
     * 删除指定的约球圈回复并将最新的约球圈帖子下的回复与评论返回
     * @param replyId   回复信息的ID
     * @param request   HTTP请求
     * @return          返回回复里最新的消息列表
     */
    @Transactional
    @Override
    public AppointmentCommentsListDto deleteReply(CommentAndReplyId replyId, HttpServletRequest request){
        AppointmentCommentsListDto result = new AppointmentCommentsListDto();
    	if (getUserIdFromRequest(request) == replyMapper.selectByPrimaryKey(replyId.getId()).getUserId()){
            replyMapper.updateToInvisibleByPrimaryKey(replyId.getId());
            int commentId = replyMapper.selectByPrimaryKey(replyId.getId()).getCommentId();
            int appointmentID = commentMapper.selectByPrimaryKey(commentId).getAid();
            List<AppointmentCommentsDto> commentsAndReplies = this.getCommentsAndReplies(appointmentID);
            int commentNums = this.getCommentNums(commentsAndReplies);
            result.setCommentsAndReplies(commentsAndReplies);
            result.setCommentsNum(commentNums);
            return result;
        }else{
            throw new ServiceException("您无权删除他人的评论");
        }
    }

    @Transactional
	@Override
	public void insertMessage(Integer id, Integer type,Integer senderId,Integer receiverId) {
		AppointmentMessage msg = new AppointmentMessage();
		msg.setBid(id);
		msg.setType(type);
		msg.setSenderId(senderId);
		msg.setReceiverId(receiverId);
		msg.setCreated(new Date());
		msg.setStatus(false);
		msgMapper.insert(msg);
	}

	@Override
	public AppointmentComment getCommentById(Integer commentId) {
		return commentMapper.selectByPrimaryKey(commentId);
	}

	@Override
	public AppointmentCommentReply getReplyById(Integer arid) {
		return replyMapper.selectByPrimaryKey(arid);
	}
}
