package com.mmm.weixin.service.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mmm.weixin.dao.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmm.weixin.common.ResultValidate;
import com.mmm.weixin.common.TokenValidate;
import com.mmm.weixin.constants.UserConstants;
import com.mmm.weixin.consts.WeekDay;
import com.mmm.weixin.dto.AppointmentCommentsDto;
import com.mmm.weixin.dto.AppointmentDto;
import com.mmm.weixin.dto.AppointmentImagesDto;
import com.mmm.weixin.dto.AppointmentListDto;
import com.mmm.weixin.dto.AppointmentListParamDto;
import com.mmm.weixin.dto.AppointmentMessageDto;
import com.mmm.weixin.dto.AppointmentParamDto;
import com.mmm.weixin.dto.AppointmentRecommendDto;
import com.mmm.weixin.dto.BaseDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.ShopDetailParamDto;
import com.mmm.weixin.dto.ShopDto;
import com.mmm.weixin.dto.UserDto;
import com.mmm.weixin.dto.UserInfoDto;
import com.mmm.weixin.dto.param.AppointmentMsgId;
import com.mmm.weixin.dto.param.AppointmentMsgParamDto;
import com.mmm.weixin.dto.param.AppointmentPlayerParamDto;
import com.mmm.weixin.dto.param.AppointmentPlayerQueryDto;
import com.mmm.weixin.dto.param.AppointmentQueryDto;
import com.mmm.weixin.dto.param.CommentAndReplyId;
import com.mmm.weixin.dto.param.JoinedAppointmentParamDto;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.feign.SellerClient;
import com.mmm.weixin.service.IAppointmentCommentService;
import com.mmm.weixin.service.IAppointmentService;
import com.mmm.weixin.service.IUserService;
import com.mmm.weixin.utils.base.ValidateHelper;
import com.mmm.weixin.vo.Appointment;
import com.mmm.weixin.vo.AppointmentComment;
import com.mmm.weixin.vo.AppointmentCommentReply;
import com.mmm.weixin.vo.AppointmentForward;
import com.mmm.weixin.vo.AppointmentImages;
import com.mmm.weixin.vo.AppointmentMessage;
import com.mmm.weixin.vo.AppointmentPlayer;
import com.mmm.weixin.vo.User;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private TokenValidate tokenValidate;

	@Autowired
	private AppointmentMapper appointmentMapper;

	@Autowired
	private AppointmentImagesMapper imagesMapper;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAppointmentCommentService commentService;

	@Autowired
	private SellerClient sellerClient;

	@Autowired
	private AppointmentForwardMapper forwardMapper;

	@Autowired
	private AppointmentMessageMapper msgMapper;

	@Autowired
	private AppointmentPlayerMapper playerMapper;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${todayBeginDateStr}")
	private String todayBeginDateStr;
	
	@Value("${tomorrowBeginDateStr}")
	private String tomorrowBeginDateStr;
	
	@Override
	@Transactional
	public void addAppointment(AppointmentParamDto param, String authorization) {
		Integer shopId = param.getShopId();
		if (null == shopId) {
			throw new ServiceException("球场不能为空");
		}
		Integer userId = tokenValidate.validateToken(authorization);
		Appointment appointment = new Appointment();
		BeanUtils.copyProperties(param, appointment);
		appointment.setUserId(userId);
		appointment.setStatus(UserConstants.APPOINTMENT_STATUS_ACTIVE);
		appointment.setCreated(new Date());
		try {
			String timeStart = param.getMatchStartTime();
			String deadLine = param.getDeadLine();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = sdf.parse(timeStart);
			long time = startDate.getTime() - System.currentTimeMillis();
			if (time < 0) {
				throw new ServiceException("您所选择的开始时间为过去时间!");
			}
			Date deadLineDate = sdf.parse(deadLine);
			long deadLinetime = deadLineDate.getTime() - System.currentTimeMillis();
			if(deadLinetime < 0) {
				throw new ServiceException("您所选择的报名截止时间为过去时间!");
			}
			appointment.setBeginDate(startDate);
			appointment.setDeadLine(deadLineDate);
			appointmentMapper.insert(appointment);
		} catch (ParseException e) {
			throw new ServiceException("时间格式错误！");
		}
		Integer aid = appointment.getAid();
		// 插入约球图片
		List<AppointmentImagesDto> images = param.getImages();
		if (!CollectionUtils.isEmpty(images)) {
			Iterator<AppointmentImagesDto> iterator = images.iterator();
			AppointmentImages image = null;
			List<AppointmentImages> aImages = new ArrayList<AppointmentImages>();
			while (iterator.hasNext()) {
				image = new AppointmentImages();
				AppointmentImagesDto imageDto = iterator.next();
				BeanUtils.copyProperties(imageDto, image);
				image.setAid(aid);
				image.setCreated(new Date());
				aImages.add(image);
			}
			imagesMapper.insertByList(aImages);
		}
		//添加约球球员
		AppointmentPlayer player = new AppointmentPlayer();
		player.setAid(aid);
		player.setJoiner(userId);
		player.setOrganizer(userId);
		player.setStatus(UserConstants.APPOINTMENT_PLAYER_STATUS_PASS);
		player.setCreated(new Date());
		playerMapper.insert(player);
	}

	@Override
	public AppointmentListDto listAppointment(AppointmentListParamDto param, String authorization) {
		AppointmentListDto result = new AppointmentListDto();
		Integer type = param.getType();
		Integer uid =  0;
		if(type == UserConstants.CURRENT_USER_APPOINTMENT_LIST || type == UserConstants.ALL_APPOINTMENT_LIST) {
			uid = tokenValidate.validateToken(authorization);
		}else {
			uid = param.getOtherUser();
		}
		PageInfo<AppointmentDto> appointments = getAppointmentList(param, uid);
		result.setAppointments(appointments);
		return result;
	}

	@Override
	public AppointmentListDto listJoinedAppointment(JoinedAppointmentParamDto param, String authorization) {
		AppointmentListDto result = new AppointmentListDto();
		Integer uid = tokenValidate.validateToken(authorization);
		PageInfo<AppointmentDto> appointments = getJoinedAppointmentList(param.getJoinPlayerType(),
				param.getCurrentPage(), param.getPageSize(), uid);
		result.setAppointments(appointments);
		return result;
	}

	private PageInfo<AppointmentDto> getAppointmentList(AppointmentListParamDto param, Integer uid) {
		AppointmentQueryDto appointParam = new AppointmentQueryDto();
		// 根据类型判断查询全部还是当前用户的约球列表
		appointParam.setUserId(param.getType() == UserConstants.ALL_APPOINTMENT_LIST ? null : uid);
		appointParam.setSex(param.getSex());
		appointParam.setJoinPlayerType(param.getJoinPlayerType());
		PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
		List<Appointment> appointments = appointmentMapper.selectByCondition(appointParam);
		return getAppointmentDto(appointments,uid);
	}

	private PageInfo<AppointmentDto> getJoinedAppointmentList(Integer joinPlayerType, Integer pageNum, Integer pageSize,
			Integer uid) {
		AppointmentQueryDto appointParam = new AppointmentQueryDto();
		appointParam.setUserId(uid);
		appointParam.setJoinPlayerType(joinPlayerType);
		PageHelper.startPage(pageNum, pageSize);
		List<Appointment> appointments = appointmentMapper.selectByComment(appointParam);
		return getAppointmentDto(appointments);
	}

	private PageInfo<AppointmentDto> getAppointmentDto(List<Appointment> appointments,Integer...uid) {
		List<AppointmentDto> result = new ArrayList<AppointmentDto>();
		AppointmentDto appointmentDto = null;
		Iterator<Appointment> iterator = appointments.iterator();
		while (iterator.hasNext()) {
			appointmentDto = new AppointmentDto();
			Appointment appointment = iterator.next();
			BeanUtils.copyProperties(appointment, appointmentDto);
			appointmentDto.setDiscussId(appointment.getAid());
			//格式化创建时间和报名截止时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String created = sdf.format(appointment.getCreated());
			appointmentDto.setCreated(created);
			Date deadLineDate = appointment.getDeadLine();
			String deadLine = sdf.format(deadLineDate);
			appointmentDto.setDeadLine(deadLine);
			//判断报名时间是否过期
			appointmentDto.setIsExpired(deadLineDate.getTime()<new Date().getTime()?true:false);
			//解析开始时间
			String beginStr = getAppointmentBeginDate(appointment.getBeginDate());
			appointmentDto.setBeginDate(beginStr);
			// 查询用户姓名和头像
			this.resovleAppointmentUserInfo(appointmentDto);
			// 查询评论数量
			Integer aid = appointment.getAid();
			CommentAndReplyId commentParam = new CommentAndReplyId();
			commentParam.setId(aid);
			List<AppointmentCommentsDto> commentsAndReplies = commentService.getCommentsAndReplies(commentParam.getId());
			int nums = getAppointmentCommentNums(commentsAndReplies);
			appointmentDto.setCommentsNum(nums);
			// 查询该约球圈的评论和回复
			appointmentDto.setCommentsAndReplies(commentService.getCommentsAndReplies(aid));
			// 查询约球图片
			List<AppointmentImages> images = imagesMapper.selectByAid(aid);
			List<AppointmentImagesDto> imagesDto = convertAppointmentImageDto(images);
			appointmentDto.setImages(imagesDto);
			// 解析球场信息
			String shopName = resolveAppointmentShopInfo(appointmentDto);
			appointmentDto.setShopName(shopName);
			// 查询参加约球的人
			Set<UserInfoDto> joiners = this.getAppointmentJoiners(aid);
			appointmentDto.setJoiners(joiners);
			//查询当前用户的约球状态
			if(ValidateHelper.isNotEmptyArray(uid)) {
				appointmentDto.setJoinStatus(resolveJoinerStatus(aid, joiners, uid[0]));
			}
			result.add(appointmentDto);
		}
		return new PageInfo<AppointmentDto>(result);
	}

	private String getAppointmentBeginDate(Date beginDate) {
		SimpleDateFormat sdf;
		//开始时间的时分
		String beginDateHourAndMinute = new SimpleDateFormat("HH:mm").format(beginDate);
		Calendar currentCal = Calendar.getInstance();
		Calendar beginDateCal = Calendar.getInstance();
		beginDateCal.setTime(beginDate);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String beginStr = "";
		//今天时间
		long currentDayMilli=0;
		//比赛开始时间
		long beginDayMilli=0;
		//明天时间
		long tomorrowDayMilli=0;
		int beginDateNum = 0;
		int actualMonth = 0;
		Date current = new Date();
		try {
			beginDayMilli = sdf.parse(sdf.format(beginDateCal.getTime().getTime())).getTime();
			//比赛开始日期（几号）
			beginDateNum = beginDateCal.get(Calendar.DAY_OF_MONTH);
			//比赛开始月份
			actualMonth = beginDateCal.get(Calendar.MONTH)+1;
			currentCal.setTime(current);
			currentDayMilli = sdf.parse(sdf.format(currentCal.getTime().getTime())).getTime();
			currentCal.add(Calendar.DAY_OF_MONTH, 1);
			tomorrowDayMilli = sdf.parse(sdf.format(currentCal.getTime().getTime())).getTime();
		}catch(Exception e) {
			logger.error("getAppointmentDto异常:",e);
		}
		if(currentDayMilli==beginDayMilli) {
			beginStr = MessageFormat.format(todayBeginDateStr, actualMonth,beginDateNum,beginDateHourAndMinute);
		}else if(tomorrowDayMilli==beginDayMilli){
			beginStr = MessageFormat.format(tomorrowBeginDateStr, actualMonth,beginDateNum,beginDateHourAndMinute);
		}else {
			beginStr = WeekDay.typeOf(beginDateCal.get(Calendar.DAY_OF_WEEK)).getDescription()+"("+actualMonth+"/"+beginDateNum+") "+beginDateHourAndMinute;
		}
		return beginStr;
	}

	private Integer resolveJoinerStatus(Integer aid, Set<UserInfoDto> joiners,Integer uid) {
			Iterator<UserInfoDto> joinerIterator = joiners.iterator();
			while(joinerIterator.hasNext()) {
				UserInfoDto userInfoDto = joinerIterator.next();
				if(userInfoDto.getUserID()==uid) {
					return UserConstants.APPOINTMENT_PLAYER_STATUS_PASS;
				}
			}
			AppointmentPlayerQueryDto param = new AppointmentPlayerQueryDto();
			param.setJoiner(uid);
			param.setAid(aid);
			List<AppointmentPlayer> players = playerMapper.selectByCondition(param);
			if(ValidateHelper.isNotEmptyCollection(players)) {
				return players.get(0).getStatus();
			}
			return null;
	}

	private Set<UserInfoDto> getAppointmentJoiners(Integer aid) {
		AppointmentPlayerQueryDto param = new AppointmentPlayerQueryDto();
		param.setAid(aid);
		param.setStatus(UserConstants.APPOINTMENT_PLAYER_STATUS_PASS);
		List<AppointmentPlayer> players = playerMapper.selectByCondition(param);
		Set<UserInfoDto> result = new HashSet<UserInfoDto>();
		Iterator<AppointmentPlayer> iterator = players.iterator();
		UserInfoDto userDto = null;
		while (iterator.hasNext()) {
			userDto = new UserInfoDto();
			AppointmentPlayer next = iterator.next();
			Integer joiner = next.getJoiner();
			User user = userService.listUserByUserId(Arrays.asList(joiner)).get(0);
			userDto.setUserID(user.getUserId());
			userDto.setNickName(user.getNickName());
			userDto.setThumbNailUrl(user.getHeadUrl());
			userDto.setSex(user.getSex());
			List<String> tags = new ArrayList<String>();
			String tagsStr = user.getTags();
			if (ValidateHelper.isNotEmptyString(tagsStr)) {
				tags = Arrays.asList(tagsStr.split(","));
			}
			userDto.setTags(tags);
			result.add(userDto);
		}
		return result;
	}

	private int getAppointmentCommentNums(List<AppointmentCommentsDto> commentsAndReplies) {
		int nums = 0;
		Iterator<AppointmentCommentsDto> iterator = commentsAndReplies.iterator();
		while (iterator.hasNext()) {
			++nums;
			AppointmentCommentsDto next = iterator.next();
			nums += next.getReplies().size();
		}
		return nums;
	}

	private String resolveAppointmentShopInfo(AppointmentDto appointmentDto) {
		ShopDetailParamDto shopParam = new ShopDetailParamDto();
		shopParam.setShopId(appointmentDto.getShopId());
		Result sellerResult = sellerClient.getShopById(shopParam);
		ResultValidate.validateResult(sellerResult);
		return JSON.parseObject(JSON.toJSONString(sellerResult.getData()), new TypeReference<ShopDto>() {}).getShopName();
	}

	private List<AppointmentImagesDto> convertAppointmentImageDto(List<AppointmentImages> images) {
		List<AppointmentImagesDto> result = new ArrayList<AppointmentImagesDto>();
		Iterator<AppointmentImages> iterator = images.iterator();
		AppointmentImagesDto imageDto = null;
		while (iterator.hasNext()) {
			AppointmentImages image = iterator.next();
			imageDto = new AppointmentImagesDto();
			BeanUtils.copyProperties(image, imageDto);
			result.add(imageDto);
		}
		return result;
	}

	private void resovleAppointmentUserInfo(AppointmentDto appointmentDto) {
		List<Integer> uids = Arrays.asList(appointmentDto.getUserId());
		User user = userService.listUserByUserId(uids).get(0);
		appointmentDto.setHeadUrl(user.getHeadUrl());
		appointmentDto.setUserName(user.getNickName());
		appointmentDto.setSex(user.getSex());
		String tagsStr = user.getTags();
		List<String> tags = new ArrayList<String>();
		if (ValidateHelper.isNotEmptyString(tagsStr)) {
			tags = Arrays.asList(tagsStr.split(","));
		}
		appointmentDto.setTags((tags));
	}

	@Transactional
	@Override
	public Integer addAppointmentForward(CommentAndReplyId param, String authorization) {
		Integer id = param.getId();
		Appointment appointment = appointmentMapper.selectByPrimaryKey(id);
		if (null == appointment) {
			throw new ServiceException("约球圈不存在");
		}
		Integer uid = tokenValidate.validateToken(authorization);
		AppointmentForward forward = new AppointmentForward();
		forward.setAid(id);
		forward.setSendUserId(uid);
		forward.setCreated(new Date());
		forwardMapper.insert(forward);
		// 修改约球圈分享数
		Integer shareCount = appointment.getShareCount();
		shareCount = shareCount == null ? 0 : shareCount;
		appointment.setShareCount(++shareCount);
		// 新增约球圈消息
		commentService.insertMessage(forward.getAfid(), UserConstants.APPOINTMENT_MESSAGE_TYPE_FORWARD, uid,appointment.getUserId());
		appointmentMapper.updateByPrimaryKey(appointment);
		return shareCount;
	}

	@Override
	public Map<String, Object> listAppointmentMsg(BaseDto baseDto, String authorization) {
		Map<String, Object> result = new HashMap<String, Object>();
		Integer uid = tokenValidate.validateToken(authorization);
		AppointmentMsgParamDto param = new AppointmentMsgParamDto();
		param.setUid(uid);
		param.setIsPlayer(false);
		PageHelper.startPage(baseDto.getCurrentPage(), baseDto.getPageSize());
		// 查询非约球消息
		List<AppointmentMessage> appointmentMsges = msgMapper.selectByUid(param);
		param.setIsPlayer(true);
		PageHelper.startPage(baseDto.getCurrentPage(), baseDto.getPageSize());
		// 查询约球消息
		List<AppointmentMessage> playerMsges = msgMapper.selectByUid(param);

		List<AppointmentMessageDto> otherMsgDtos = resolveAppointmentMessageDtoList(appointmentMsges);
		List<AppointmentMessageDto> playerMsgDtos = resolveAppointmentMessageDtoList(playerMsges);
		result.put("appointmentPlayerMsg", new PageInfo(playerMsgDtos));
		result.put("otherMsg", new PageInfo(otherMsgDtos));
		return result;
	}

	private List<AppointmentMessageDto> resolveAppointmentMessageDtoList(List<AppointmentMessage> appointmentMsges) {
		List<AppointmentMessageDto> result = new ArrayList<AppointmentMessageDto>();
		Iterator<AppointmentMessage> iterator = appointmentMsges.iterator();
		while (iterator.hasNext()) {
			AppointmentMessage appointmentMessage = iterator.next();
			AppointmentMessageDto dto = convert2AppointmentMessageDto(appointmentMessage);
			if (appointmentMessage.getType() == UserConstants.APPOINTMENT_MESSAGE_TYPE_JOIN) {
				AppointmentPlayer appointmentPlayer = playerMapper.selectByPrimaryKey(appointmentMessage.getBid());
				dto.setApplyStatus(appointmentPlayer.getStatus());
				dto.setAppointmentPlayerId(appointmentPlayer.getApid());
			}
			result.add(dto);
		}
		return result;
	}

	private AppointmentMessageDto convert2AppointmentMessageDto(AppointmentMessage appointmentMessage) {
		AppointmentMessageDto dto = new AppointmentMessageDto();
		Integer type = appointmentMessage.getType();
		Integer bid = appointmentMessage.getBid();
		Integer senderId = appointmentMessage.getSenderId();
		User user = userService.listUserByUserId(Arrays.asList(senderId)).get(0);
		dto.setAmid(appointmentMessage.getAmid());
		dto.setStatus(appointmentMessage.getStatus());
		dto.setUid(user.getUserId());
		dto.setHeadUrl(user.getHeadUrl());
		dto.setName(user.getNickName());
		dto.setType(type);
		int appointmentId = 0;
		String publishTime = "";
		// 如果是评论或者回复通过查询对应的comment，获取约球ID
		if (UserConstants.APPOINTMENT_MESSAGE_TYPE_COMMENT == type || UserConstants.APPOINTMENT_MESSAGE_TYPE_REPLY == type) {
			AppointmentComment comment = new AppointmentComment();
			if (UserConstants.APPOINTMENT_MESSAGE_TYPE_COMMENT == type) {
				comment = commentService.getCommentById(bid);
				dto.setContent(comment.getContent());
				publishTime = commentService.calculateTime(comment.getCreated());
			} else {
				AppointmentCommentReply reply = commentService.getReplyById(bid);
				dto.setContent(reply.getContent());
				publishTime = commentService.calculateTime(reply.getCreated());
				comment = commentService.getCommentById(reply.getCommentId());
			}
			appointmentId = comment.getAid();
			dto.setAppointmentId(appointmentId);
		} else if (UserConstants.APPOINTMENT_MESSAGE_TYPE_FORWARD == type) { // 如果是转发获取转发的约球ID
			AppointmentForward forward = forwardMapper.selectByPrimaryKey(bid);
			appointmentId = forward.getAid();
			publishTime = commentService.calculateTime(forward.getCreated());
			dto.setAppointmentId(appointmentId);
		} else if (UserConstants.APPOINTMENT_MESSAGE_TYPE_JOIN == type) { // 申请参加约球
			AppointmentPlayer appPlayer = playerMapper.selectByPrimaryKey(bid);
			appointmentId = appPlayer.getAid();
			dto.setAppointmentId(appointmentId);
			Appointment appointment = appointmentMapper.selectByPrimaryKey(appointmentId);
			dto.setContent(appointment.getContent());
		}
		setAppointmentInfo(dto);
		dto.setPublishTime(publishTime);
		dto.setAppointmentImage(getAppointmentMainImg(appointmentId));
		return dto;
	}

	private void setAppointmentInfo(AppointmentMessageDto dto) {
		Integer appointmentId = dto.getAppointmentId();
		Appointment appointment = appointmentMapper.selectByPrimaryKey(appointmentId);
		dto.setTitle(appointment.getTitle());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String appointmentBeginDate = this.getAppointmentBeginDate(appointment.getBeginDate());
		dto.setBeginDate(appointmentBeginDate);
		dto.setDeadLine(sdf.format(appointment.getDeadLine()));
	}

	// 设置消息的主图
	private String getAppointmentMainImg(Integer aid) {
		List<AppointmentImages> imgs = imagesMapper.selectByAid(aid);
		Iterator<AppointmentImages> iterator = imgs.iterator();
		while (iterator.hasNext()) {
			AppointmentImages next = iterator.next();
			if (next.getIsMain()) {
				return next.getImageUrl();
			}
		}
		return null;
	}

	@Override
	public void readAppointmentMessage(AppointmentMsgId param, String authorization) {
		Integer uid = tokenValidate.validateToken(authorization);
		Integer amid = param.getAmid();
		AppointmentMessage msg = msgMapper.selectByPrimaryKey(amid);
		if (null == msg) {
			throw new ServiceException("该消息不存在");
		}
		if (uid != msg.getReceiverId()) {
			throw new ServiceException("没有阅读消息权限");
		}
		msg.setStatus(true);
		msg.setUpdated(new Date());
		msgMapper.updateByPrimaryKey(msg);
	}

	@Override
	public int getUnreadMessageNum(String authorization) {
		Integer uid = tokenValidate.validateToken(authorization);
		return msgMapper.selectUnreadByUid(uid);
	}

	@Override
	public AppointmentDto getAppointment(AppointmentMsgId msgId,String authorization) {
		Integer uid = tokenValidate.validateToken(authorization);
		int appointmentID = msgId.getAmid();
		Appointment appointment = appointmentMapper.selectByPrimaryKey(appointmentID);
		List<Appointment> appointmentList = Arrays.asList(appointment);
		return this.getAppointmentDto(appointmentList,uid).getList().get(0);
	}

	@Override
	public Integer insertAppointmentPlayer(AppointmentMsgId appointmentMsgId, String authorization) {
		Integer joinerId = tokenValidate.validateToken(authorization);
		Integer amid = appointmentMsgId.getAmid();
		validateJoinerIsFull(amid);
		Appointment appointment = appointmentMapper.selectByPrimaryKey(amid);
		if (null == appointment) {
			throw new ServiceException("约球信息不存在");
		}
		long currentTime = new Date().getTime();
		long deadLineTime = appointment.getDeadLine().getTime();
		if(currentTime>deadLineTime) {
			throw new ServiceException("报名时间已过期");
		}
		Integer creatorId = appointment.getUserId();
		if (joinerId == creatorId) {
			throw new ServiceException("不能加入自己创建的约球");
		}
		AppointmentPlayerQueryDto param = new AppointmentPlayerQueryDto();
		param.setAid(amid);
		param.setJoiner(joinerId);
		List<AppointmentPlayer> players = playerMapper.selectByCondition(param);
		if (ValidateHelper.isNotEmptyCollection(players)) {
			throw new ServiceException("你已申请加入");
		}
		AppointmentPlayer player = new AppointmentPlayer();
		player.setAid(amid);
		player.setJoiner(joinerId);
		player.setOrganizer(creatorId);
		player.setStatus(UserConstants.APPOINTMENT_PLAYER_STATUS_REPLYING);
		player.setCreated(new Date());
		playerMapper.insert(player);
		Integer apid = player.getApid();
		commentService.insertMessage(apid,UserConstants.APPOINTMENT_MESSAGE_TYPE_JOIN, joinerId,creatorId);
		return playerMapper.selectByPrimaryKey(apid).getStatus();
	}

	private void validateJoinerIsFull(Integer amid) {
		AppointmentPlayerQueryDto param = new AppointmentPlayerQueryDto();
		param.setAid(amid);
		List<AppointmentPlayer> players = playerMapper.selectByCondition(param);
		Integer joinerNum = 0;
		Iterator<AppointmentPlayer> iterator = players.iterator();
		while(iterator.hasNext()) {
			AppointmentPlayer next = iterator.next();
			if(next.getStatus()!=UserConstants.APPOINTMENT_PLAYER_STATUS_REJECT) {
				joinerNum++;
			}
		}
		Appointment appointment = appointmentMapper.selectByPrimaryKey(amid);
		if(joinerNum == appointment.getNumber()) {
			throw new ServiceException("参赛人数已满,请关注其他赛事");
		}
	}

	@Override
	public void updateAppointmentPlayer(AppointmentPlayerParamDto param, String authorization) {
		Integer uid = tokenValidate.validateToken(authorization);
		Integer appointmentPlayerId = param.getAppointmentPlayerId();
		AppointmentPlayer appPlayer = playerMapper.selectByPrimaryKey(appointmentPlayerId);
		if (appPlayer.getStatus() != UserConstants.APPOINTMENT_PLAYER_STATUS_REPLYING) {
			throw new ServiceException("申请已审核，无法修改");
		}
		if (uid != appPlayer.getOrganizer()) {
			throw new ServiceException("你没有操作权限");
		}
		int status = param.getIsAgree() ? UserConstants.APPOINTMENT_PLAYER_STATUS_PASS: UserConstants.APPOINTMENT_PLAYER_STATUS_REJECT;
		appPlayer.setStatus(status);
		appPlayer.setUpdated(new Date());
		playerMapper.updateByPrimaryKey(appPlayer);
	}

	@Override
	public List<AppointmentRecommendDto> listRecommendAppointment() {
		AppointmentQueryDto appointmentParam = new AppointmentQueryDto();
		appointmentParam.setIsRecommend(UserConstants.APPOINTMENT_IS_RECOMMEND);
		List<Appointment> appointments = appointmentMapper.selectByCondition(appointmentParam);
		return resolveRecommendAppointment(appointments);
	}

	private List<AppointmentRecommendDto> resolveRecommendAppointment(List<Appointment> appointments) {
		List<AppointmentRecommendDto> result = new ArrayList<AppointmentRecommendDto>();
		Iterator<Appointment> iterator = appointments.iterator();
		AppointmentRecommendDto appointmentRecommendDto = null;
		while(iterator.hasNext()) {
			appointmentRecommendDto = new AppointmentRecommendDto();
			Appointment appointment = iterator.next();
			BeanUtils.copyProperties(appointment, appointmentRecommendDto);
			AppointmentImages images = imagesMapper.selectMainPicByAid(appointment.getAid());
			appointmentRecommendDto.setImageUrl(images.getImageUrl());
			//查询用户信息
			UserDto userInfo = userService.getUserInfo(appointment.getUserId());
			BeanUtils.copyProperties(userInfo, appointmentRecommendDto);
			result.add(appointmentRecommendDto);
		}
		return result;
	}

}
