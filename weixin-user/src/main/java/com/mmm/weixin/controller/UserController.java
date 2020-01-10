package com.mmm.weixin.controller;


import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.mmm.weixin.dto.param.*;
import com.mmm.weixin.dto.qrcode.QRCodeDto;
import com.mmm.weixin.vo.Comments;
import com.mmm.weixin.vo.CommentsVo;
import com.mmm.weixin.vo.Feedback;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mmm.weixin.dto.AddressParamDto;
import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.CommentParamDto;
import com.mmm.weixin.dto.CommentQueryDto;
import com.mmm.weixin.dto.CommentReplyQueryDto;
import com.mmm.weixin.dto.ListUserParamDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.ShopCommentQueryDto;
import com.mmm.weixin.dto.UserIdParamDto;
import com.mmm.weixin.dto.UserParamDto;
import com.mmm.weixin.service.IUserService;
import com.mmm.weixin.utils.jwt.JwtUtil;

import io.swagger.annotations.ApiImplicitParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
//@RequestMapping("user")
public class UserController extends BaseController {

    //熔断提示信息,在weixin-common的resources-application-common.yml
    @Value("${hystrix_error}")
    private String hystrixError;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    @Resource
    private JwtUtil jwtUtil;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @ApiImplicitParam(name = "authorization", value = "勿填", required = false, dataType = "String", paramType = "header")
    public Result login(@RequestBody UserParamDto param) {
        return success(userService.login(param));
    }

    @ApiOperation("发送短信")
    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestBody SendMsgDto sendMsgDto,HttpServletRequest request) {
        userService.sendMsg(sendMsgDto,request.getHeader("authorization"));
        return success();
    }

    @ApiOperation("绑定、修改手机")
    @PostMapping("/updatePhone")
    public Result updatePhone(@RequestBody UpdatePhoneDto updatePhoneDto,HttpServletRequest request) {
        userService.updatePhoneNum(updatePhoneDto,request.getHeader("authorization"));
        return success();
    }

    @ApiOperation("修改手机下一步")
    @PostMapping("/updatePhoneNext")
    public Result updatePhoneNext(@RequestBody UpdatePhoneDto updatePhoneDto,HttpServletRequest request) {
        userService.updatePhoneNext(updatePhoneDto,request.getHeader("authorization"));
        return success();
    }

    @ApiOperation("获取用户信息")
    @PostMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        return success(userService.getUserInfo(request.getHeader("authorization")));
    }

    @ApiOperation("保存用户信息")
    @PostMapping("/saveUserInfo")
    public Result saveUserInfo(@RequestBody SaveUserInfoDto saveUserInfoDto,HttpServletRequest request) {
        return success(userService.saveUserInfo(saveUserInfoDto,request.getHeader("authorization")));
    }

    @ApiOperation("用户收藏")
    @PostMapping("/userCollect")
    public Result getUserCollectList(@RequestBody GetUserInfoDto getUserInfoDto,HttpServletRequest request) {
        return success(userService.getUserCollectList(getUserInfoDto,request.getHeader("authorization")));
    }

    @ApiOperation("意见反馈")
    @PostMapping("/saveFeedBack")
    public Result saveFeedBack(@RequestBody Feedback feedbackDto,HttpServletRequest request) {
        userService.saveFeedBack(feedbackDto,request.getHeader("authorization"));
        return success();
    }

    @ApiOperation("评价球场")
    @PostMapping("/toSaveComments")
    public Result toSaveComments(@RequestBody CommentsVo commentsVo) {
        return success(userService.toSaveComments(commentsVo));
    }

    @ApiOperation("发布评价")
    @PostMapping("/saveComment")
    @ApiImplicitParam(name = "authorization", value = "用户token")
    public Result saveComment(@RequestBody Comments commentsDto,HttpServletRequest request) {
        userService.saveComment(commentsDto,request.getHeader("authorization"));
        return success();
    }

    @ApiIgnore
    @ApiOperation("查询商家评价列表")
    @PostMapping("/listCommentByShop")
    @ApiImplicitParam(name = "authorization", value = "Authorization", required = false)
    public Result listCommentByShop(@RequestBody ShopCommentQueryDto shopCommentQueryDto) {
        CommentParamDto param = new CommentParamDto();
        BeanUtils.copyProperties(shopCommentQueryDto, param);
        return success(userService.listCommentByCondition(param));
    }

    @ApiOperation("查询用户评价列表,Authorization非必填")
    @PostMapping("/listCommentByUser")
    @ApiImplicitParam(name = "authorization", value = "Authorization", required = false)
    public Result listCommentByUser(@RequestBody CommentQueryDto commentQueryDto) {
        CommentParamDto param = new CommentParamDto();
        BeanUtils.copyProperties(commentQueryDto, param);
        return success(userService.listCommentByCondition(param));
    }

    @ApiOperation("新增回复")
    @PostMapping("/addReplyComment")
    @ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
    public Result addCommentReply(@RequestBody CommentReplyQueryDto param, HttpServletRequest request) {
        userService.addCommentReply(param, request.getHeader("authorization"));
        return success();
    }

    @ApiOperation("查询用户位置信息")
    @PostMapping("/getUserAddress")
    public Result getUserAddress(@RequestBody AddressParamDto param) {
        return success(userService.getUserAddress(param));
    }

    @ApiOperation("关于我们")
    @GetMapping("/getAboutUs")
    public ResponseEntity<String> getAboutUs() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAboutUs());
    }

    @PostMapping("listUserByUserId")
    public Result listUserByUserId(@RequestBody ListUserParamDto param) {
        return success(userService.listUserByUserId(param.getUserIds()));
    }

    @ApiOperation("获取小程序码")
    @PostMapping("/qrcode")
    @ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = false)
    public ResponseEntity<String> getQRCode(@RequestBody QRCodeDto dto) {
        return ResponseEntity.ok(userService.getQRCode(dto));
    }
    
    @ApiOperation("根据ID查询用户")
    @PostMapping("/getUserById")
    @ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = false)
    public Result getUserById(@RequestBody UserIdParamDto param) {
    	return success(userService.getUserInfo(param.getUserId()));
    }
}
