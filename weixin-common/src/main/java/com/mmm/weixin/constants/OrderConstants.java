package com.mmm.weixin.constants;

public class OrderConstants {
	//订单类型 1 高尔夫
	public static final Integer ORDER_TYPE_GOLF = 1;
	
	//订单类型 3 团队赛事
	public static final Integer ORDER_TYPE_CONTEST = 3;
	
	//支付方式 1 微信
	public static final Integer PAY_METHOD_WX = 1;
	//-1. 已退款 
	public static final Integer STATE_CODE_REFUNDED = -1;
	//0.已取消  
	public static final Integer STATE_CODE_CANCELLED = 0;
	//1.待确认
	public static final Integer STATE_CODE_NOT_CONFIRM = 1;
	//2.待付款
	public static final Integer STATE_CODE_NOT_PAY = 2;
	//3.待使用
	public static final Integer STATE_CODE_NOT_USE = 3;
	//4.待评价 
	public static final Integer STATE_CODE_NOT_COMMENT = 4;
	
	public static final String ORDER_FIELD_PLAYING_DATE = "PlayingDate";
	
	public static final String ORDER_FIELD_PLAYING_TIME = "PlayingTime";
	
	public static final String ORDER_FIELD_PLAYING_USER_COUNT = "PlayingUserCount";
	
	public static final String ORDER_FIELD_FULL_NAME = "FullName";
	
	public static final String ORDER_FIELD_CONTACT_PHONE = "ContactPhone";
	
	public static final String ORDER_FIELD_CONTEST_TITLE = "ContestTitle";
	
	public static final String ORDER_FIELD_CONTEST_TIME = "ContestTime";

	public static final String ORDER_FIELD_CONTEST_ADDRESS = "ContestAddress";
	
	public static final String ORDER_FIELD_TEAM_NAME = "TeamName";
	
	public static final String ORDER_FIELD_CONTACT_NAME = "ContactName";
	
}
