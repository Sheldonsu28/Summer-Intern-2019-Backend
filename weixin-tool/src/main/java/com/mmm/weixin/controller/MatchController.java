package com.mmm.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import com.mmm.weixin.dto.*;
import com.mmm.weixin.dto.param.*;
import com.sun.net.httpserver.Authenticator;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.MatchShopParamDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.service.IMatchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@Api("比赛")
@RestController
public class MatchController extends BaseController{

	@Autowired
	private IMatchService matchService;
	
    @Autowired
    public SimpMessagingTemplate template; 
	
	@ApiOperation("查询比赛球场列表")
	@PostMapping("/getMatchShopList")
	//@ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
	public Result getMatchShopList(@RequestBody MatchShopParamDto param,HttpServletRequest request) {
		return success(matchService.listMatchShop(param,request.getHeader("authorization")));
	}
	
	@ApiOperation("查询当前用户参与的赛事")
	@PostMapping("/getMatchList")
	//@ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
	public Result getMatchList(@RequestBody UserMatchParamDto param, HttpServletRequest request) {
		return success(matchService.listMatchByUser(param,request.getHeader("authorization")));
	}
	
	
	@ApiOperation("查询赛事详情")
	@PostMapping("/getMatchDetail")
	public Result getMatchDetail(@RequestBody MatchDetailParamDto param,HttpServletRequest request) {
		return success(matchService.getMatchDetail(param,request.getHeader("authorization")));
	}

	@ApiOperation("查询好友列表")
	@PostMapping("/getPlayerList")

	public Result getUserList(@RequestBody MatchHistoryParamDto searchParam, HttpServletRequest request){
		return success(matchService.userListResult(searchParam.getUsername(), request));
	}
    @ApiOperation("创建比赛")
    @PostMapping("/createMatch")
	public Result createMatch(@RequestBody MatchInfoDto infoDto,HttpServletRequest request){
		return success(matchService.createMatch(infoDto,request));
    }
    @ApiOperation("分数更新")
	@PostMapping("/updateResult")
	public Result updateResult(@RequestBody ResultDto resultDto,HttpServletRequest request){
		return success(matchService.updateResult(resultDto,request.getHeader("authorization")));
	}
	@ApiOperation("比赛信息更新")
	@PostMapping("/updateMatchInfo")
	public Result updateMatchInfo(@RequestBody MatchInfoDto matchInfoDto,HttpServletRequest request){
		matchService.updateMatchInfo(matchInfoDto,request);
		return success();
	}

	@ApiOperation("通过小程序码添加球员")
	@PostMapping("/addPlayerFromMiniProgramCode")
    public Result addPlayerFromMiniProgramCode(@RequestBody MiniProgramPlayerParamDto param,HttpServletRequest request) {
		return success(matchService.addPlayerFromMiniProgramCode(param,request.getHeader("authorization")));
    }

	@ApiOperation("结束比赛")
	@PostMapping("/finishMatch")
	public Result finishMatch(@RequestBody FinishMatchParamDto param,HttpServletRequest request) {
		return success(matchService.finishMatch(param,request.getHeader("authorization")));
	}
	
	@ApiOperation("查询结束比赛的详情")
	@PostMapping("/getfinishMatchDetail")
	@ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = false)
	public Result getfinishMatchDetail(@RequestBody FinishMatchParamDto param) {
		return success(matchService.getfinishMatchDetail(param));
	}
	
	@ApiOperation("退赛")
	@PostMapping("/quitMatch")
	public Result quitMatch(@RequestBody MatchDetailParamDto param,HttpServletRequest request) {
		return success(matchService.quitMatch(param,request.getHeader("authorization")));
	}
	@ApiOperation("加入曾一起参与过比赛的玩家")
	@PostMapping("/addPlayerFromMatchHistory")
	public Result addPlayerFromMatchHistory(@RequestBody MatchInfoDto matchInfoDto,HttpServletRequest request){
		matchService.addPlayersFromMatchHistory(matchInfoDto,request);
		return success();
	}
	
	@ApiOperation("查询球场区域")
	@PostMapping("/getMatchArea")
	@ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = false)
	public Result getMatchArea(@RequestBody MatchAreaParamDto param) {
		return success(matchService.getMatchArea(param));
	}
}
