package com.mmm.weixin.constants;

public class ToolConstants {
	//附近
	public static final Integer SORT_TYPE_NEIGHBOUR = 1;
	//经常
	public static final Integer SORT_TYPE_PLAYED = 2;
	/*比赛状态*/
	public static final Integer MATCH_STATUS_READY = 1;
	
	public static final Integer MATCH_STATUS_PLAYING = 2;

	public static final Integer MATCH_STATUS_FINISH = 3;
	//添加球员
	public static final Integer WEBSOCKET_MESSAGE_TYPE_ADD_MEMEBER= 2;
	//删除球员
	public static final Integer WEBSOCKET_MESSAGE_TYPE_REMOVE_MEMEBER= 3;
	//记录成绩
	public static final Integer WEBSOCKET_MESSAGE_TYPE_MARK_RESULT= 4;
	//创建比赛
	public static final Integer WEBSOCKET_MESSAGE_TYPE_CREATE_MATCH = 5;
	//结束比赛
	public static final Integer WEBSOCKET_MESSAGE_TYPE_FINISH_MATCH = 6;
	//球员
	public static final Integer MATCH_ROLE_PLAYER=1;
	//球童
	public static final Integer MATCH_ROLE_CADDIE=2;
	//没有球童
	public static final Integer MATCH_HAS_NOT_CADDIE=0;
	//有球童
	public static final Integer MATCH_HAS_CADDIE=1;
}
