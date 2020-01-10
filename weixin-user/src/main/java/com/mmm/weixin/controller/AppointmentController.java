package com.mmm.weixin.controller;

import com.mmm.weixin.dto.AppointmentListParamDto;
import com.mmm.weixin.dto.AppointmentParamDto;
import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.BaseDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.param.AppointmentMsgId;
import com.mmm.weixin.dto.param.AppointmentPlayerParamDto;
import com.mmm.weixin.dto.param.CommentAndReplyId;
import com.mmm.weixin.dto.param.JoinedAppointmentParamDto;
import com.mmm.weixin.dto.param.PostCommentsDto;
import com.mmm.weixin.service.IAppointmentService;
import com.mmm.weixin.service.impl.AppointmentCommentServiceImp;

import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api("约球圈")
@RestController
public class AppointmentController extends BaseController {
    @Autowired
    private AppointmentCommentServiceImp appointmentCommentService;
    
    @Autowired
    private IAppointmentService appointmentServiceImpl;

    @ApiOperation("新增约球")
    @PostMapping("/addAppointment")
    public Result addAppointment(@RequestBody AppointmentParamDto param,HttpServletRequest request) {
    	appointmentServiceImpl.addAppointment(param, request.getHeader("authorization"));
    	return success();
    }

    @ApiOperation("查询约球列表")
    @PostMapping("/listAppointment")
    public Result listAppointment(@RequestBody AppointmentListParamDto param,HttpServletRequest request) {
    	return success(appointmentServiceImpl.listAppointment(param,request.getHeader("authorization")));
    }
    
    @ApiOperation("查询我的参加约球列表")
    @PostMapping("/listJoinedAppointment")
    public Result listJoinAppointment(@RequestBody JoinedAppointmentParamDto param,HttpServletRequest request) {
    	return success(appointmentServiceImpl.listJoinedAppointment(param,request.getHeader("authorization")));
    }

    @ApiOperation("删除指定的评论")
    @PostMapping("/deleteAppointmentOrComment")
    public Result deleteComment(@RequestBody CommentAndReplyId commentId,HttpServletRequest request){
        return success(appointmentCommentService.deleteComment(commentId,request));
    }

    @ApiOperation("加入新评论")
    @PostMapping("/insertComment")
    public Result insetComment(@RequestBody PostCommentsDto comments, HttpServletRequest request){
        return success(appointmentCommentService.insertComment(comments,request));
    }

    @ApiOperation("新增转发约球圈")
    @PostMapping("/addAppointmentForward")
    public Result addAppointmentForward(@RequestBody CommentAndReplyId param,HttpServletRequest request) {
    	return success(appointmentServiceImpl.addAppointmentForward(param,request.getHeader("authorization")));
    }
    
    @ApiOperation("查询消息列表")
    @PostMapping("/listAppointmentMessage")
    public Result listAppointmentMessage(@RequestBody BaseDto baseDto,HttpServletRequest request) {
    	return success(appointmentServiceImpl.listAppointmentMsg(baseDto, request.getHeader("authorization")));
    }
    
    @ApiOperation("阅读消息")
    @PostMapping("/readAppointmentMessage")
    public Result readAppointmentMessage(@RequestBody AppointmentMsgId param,HttpServletRequest request) {
    	appointmentServiceImpl.readAppointmentMessage(param,request.getHeader("authorization"));
    	return success();
    }
    
    @ApiOperation("获取未读消息数量")
    @PostMapping("/getUnreadMessageNum")
    public Result getUnreadMessageNum(HttpServletRequest request) {
    	return success(appointmentServiceImpl.getUnreadMessageNum(request.getHeader("authorization")));
    }
    @ApiOperation("获取指定的约球圈详情")
    @PostMapping("/getAppointmentDetails")
    public Result getAppointmentDetails(@RequestBody AppointmentMsgId appointmentId,HttpServletRequest request){
        return success(appointmentServiceImpl.getAppointment(appointmentId,request.getHeader("authorization")));
    }
    
    @ApiOperation("申请加入约球")
    @PostMapping("/applyJoinAppointment")
    public Result applyJoinAppointment(@RequestBody AppointmentMsgId appointmentMsgId,HttpServletRequest request) {
    	return success(appointmentServiceImpl.insertAppointmentPlayer(appointmentMsgId,request.getHeader("authorization")));
    }
    
    @ApiOperation("审核加入约球")
    @PostMapping("/auditJoinAppointment")
    public Result auditJoinAppointment(@RequestBody AppointmentPlayerParamDto param,HttpServletRequest request) {
    	appointmentServiceImpl.updateAppointmentPlayer(param,request.getHeader("authorization"));
    	return success();
    }
    
    @ApiOperation("获取推荐的约球圈列表")
    @GetMapping("/listRecommendAppointment")
    @ApiImplicitParam(name = "authorization", value = "Authorization", required = false)
    public Result listRecommendAppointment() {
    	return success(appointmentServiceImpl.listRecommendAppointment());
    }
    
}
