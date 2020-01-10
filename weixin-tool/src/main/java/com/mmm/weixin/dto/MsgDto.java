package com.mmm.weixin.dto;

public class MsgDto {

	//消息类型:1加入ChatRoom 2普通内容消息 98加入ChatRoom回执消息
	private Integer messageType;
	//消息发送人(登录用户ID)
	private Integer sendUserId;
	//ChatRoomId(项目中表示赛事ID)
	private String chatRoomId;
	//消息内容
	private String messageBody;
	
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public Integer getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	
	
}
