package com.mmm.weixin.service;



import java.util.List;

import com.mmm.weixin.dto.param.*;
import com.mmm.weixin.dto.qrcode.QRCodeDto;
import com.mmm.weixin.vo.*;

import com.github.pagehelper.PageInfo;
import com.mmm.weixin.dto.AddressParamDto;
import com.mmm.weixin.dto.CommentDto;
import com.mmm.weixin.dto.CommentParamDto;
import com.mmm.weixin.dto.CommentReplyParamDto;
import com.mmm.weixin.dto.CommentReplyQueryDto;
import com.mmm.weixin.dto.LoginDto;
import com.mmm.weixin.dto.UserDto;
import com.mmm.weixin.dto.UserParamDto;

import javax.servlet.http.HttpServletRequest;


public interface IUserService {

	/*public PageInfo<User> listByPage(int currentPage,int pageSize);
	
	public List<OrderDto> list(BaseDto baseDto);
	
	*/
	public LoginDto login(UserParamDto param);
	
	PageInfo<CollectVo> getUserCollectList(GetUserInfoDto getUserInfoDto, String authorization);

	void saveFeedBack(Feedback feedbackDto,String authorization);

	void saveComment(Comments comments,String authorization);

	public PageInfo<CommentDto> listCommentByCondition(CommentParamDto param);
	
	public List<CommentsReply> listCommentsReplyByCondition(CommentReplyParamDto param);
	
	public void addCommentReply(CommentReplyQueryDto param,String authorization);

    CommentsVo toSaveComments(CommentsVo commentsVo);

    void sendMsg(SendMsgDto sendMsgDto,String authorization);

    void updatePhoneNum(UpdatePhoneDto updatePhoneDto,String authorization);

    UserDto getUserInfo(String authorization);

    Integer saveUserInfo(SaveUserInfoDto saveUserInfoDto,String authorization);

    void updatePhoneNext(UpdatePhoneDto updatePhoneDto,String authorization);

    String getUserAddress(AddressParamDto param);

    String getAboutUs();
    
    List<User> listUserByUserId(List<Integer> list);

    String getQRCode(QRCodeDto dto);
    
    UserDto getUserInfo(Integer userId);
}
