package com.mmm.weixin.service;

import java.util.List;
import java.util.Map;

import com.mmm.weixin.dto.*;
import com.mmm.weixin.dto.param.AppointmentMsgId;
import com.mmm.weixin.dto.param.AppointmentPlayerParamDto;
import com.mmm.weixin.dto.param.CommentAndReplyId;
import com.mmm.weixin.dto.param.JoinedAppointmentParamDto;

public interface IAppointmentService {

	public void addAppointment(AppointmentParamDto param,String authorization);
	
	public AppointmentListDto listAppointment(AppointmentListParamDto param,String authorization);
	
	public AppointmentListDto listJoinedAppointment(JoinedAppointmentParamDto param,String authorization);

	public Integer addAppointmentForward(CommentAndReplyId param, String authorization);
	
	public Map<String, Object> listAppointmentMsg(BaseDto baseDto, String authorization);

	public void readAppointmentMessage(AppointmentMsgId param, String authorization);

	public int getUnreadMessageNum(String header);

	public AppointmentDto getAppointment(AppointmentMsgId msgId, String authorization);

	public Integer insertAppointmentPlayer(AppointmentMsgId appointmentMsgId, String authorization);

	public void updateAppointmentPlayer(AppointmentPlayerParamDto param, String authorization);

	public List<AppointmentRecommendDto> listRecommendAppointment();

}
