package com.mmm.weixin.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmm.weixin.annotation.Token;
import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.BaseDto;
import com.mmm.weixin.dto.ContestOrderNumDto;
import com.mmm.weixin.dto.FlashSaleCommodityParamDto;
import com.mmm.weixin.dto.GolfShopParamDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.TeamInfoAddDto;
import com.mmm.weixin.dto.TeamInfoQueryDto;
import com.mmm.weixin.dto.TeamMemberAddDto;
import com.mmm.weixin.dto.TeamMemberDto;
import com.mmm.weixin.dto.TeamAndMemberDto;
import com.mmm.weixin.dto.param.ContestInfoQueryParamDto;
import com.mmm.weixin.dto.param.ContestParamDto;
import com.mmm.weixin.dto.param.GolfDetailParamDto;
import com.mmm.weixin.service.ICommodityService;
import com.mmm.weixin.vo.ContestTeamInfo;
import com.mmm.weixin.vo.ContestTeamMember;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(description="商品")
@RestController
public class CommodityController extends BaseController{

	@Autowired
	private ICommodityService commodityService;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@ApiOperation("查询高尔夫球场详情")
	@PostMapping("/getGolfDetail")
	@ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
	public Result getGolfDetail(@RequestBody GolfDetailParamDto param,HttpServletRequest request){
		return success(commodityService.getGolfDetail(param,request.getHeader("authorization")));
	}
	
	@ApiOperation("收藏高尔夫球场")
	@PostMapping("/collectGolf")
	@ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
	public Result collectGolft(@RequestBody GolfDetailParamDto param,HttpServletRequest request){
		commodityService.collectGolf(param,request.getHeader("authorization"));
		return success();
	}
	
	@ApiIgnore
	@PostMapping("/listDefaultCommodity")
	public Result getDefaultCommodityList(@RequestBody GolfShopParamDto param) {
		return success(commodityService.listDefaultCommodityByCondition(param));
	}
	
	@ApiOperation("查询推荐团队赛事")
	@GetMapping("/listRecommendContest")
	public Result getRecommendContestList() {
				return success(commodityService.listContestInfoByCondition());
	}
	
	@ApiOperation("高球团赛列表")
	@PostMapping("/listAllContest")
	public Result listAllContest(@RequestBody ContestInfoQueryParamDto param) {
		return success(commodityService.listAllContest(param));
	}
	
	@ApiOperation("团赛预订详情")
	@PostMapping("/getBookContestDetail")
	public Result getBookContestDetail(@RequestBody ContestParamDto param) {
		return success(commodityService.getBookContestDetail(param.getContestId()));
	}
	
	@ApiOperation("添加球队")
	@PostMapping("/addTeam")
	@ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
	public Result addTeam(@RequestBody TeamInfoAddDto param,HttpServletRequest request) {
		commodityService.addTeam(param,request.getHeader("authorization"));
		return success();
	}
	
	@ApiOperation("编辑球队")
	@PostMapping("/editTeam")
	@ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
	public Result editTeam(@RequestBody TeamInfoAddDto param,HttpServletRequest request) {
		commodityService.udpateTeam(param, request.getHeader("authorization"));
		return success();
	}
	
	@ApiOperation("删除球队")
	@PostMapping("/deleteTeam")
	@ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
	public Result deleteTeam(@RequestBody TeamInfoQueryDto param,HttpServletRequest request) {
		commodityService.deleteTeam(param,request.getHeader("authorization"));
		return success();
	}

	@ApiOperation("根据用户ID查询球队")
	@PostMapping("/getTeamByUserId")
	@ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
	public Result getTeamByUserId(HttpServletRequest request) {
		return success(commodityService.getTeamInfoByUserId(request.getHeader("authorization")));
	}
	
	@ApiOperation("根据ID查询球队详情")
	@PostMapping("/getTeamById")
	public Result getTeamById(@RequestBody TeamInfoQueryDto param) {
		return success(commodityService.getTeamInfoById(param));
	}
	
	@ApiIgnore
	@ApiOperation("根据ID查询团队赛事")
	@PostMapping("/getContestInfoById")
	public Result getContestInfoById(@RequestParam Integer contestId) {
		return success(commodityService.getContestInfoByid(contestId));
	}
	
	/**
	 * 添加参赛队伍和参赛队员
	 * @param param
	 * @return
	 */
	@ApiIgnore
	@PostMapping("/addContestTeam")
	public Result addContestTeam(@RequestBody ContestTeamInfo param) {
		return success(commodityService.addContestTeam(param));
	}
	
	@ApiIgnore
	@PostMapping("/getContestTeam")
	public Result getContestTeamByCondition(@RequestBody ContestTeamInfo param) {
		return success(commodityService.getContestTeamByCondition(param));
	}
	
	@ApiIgnore
	@PostMapping("/getContestTeamMember")
	public Result getContestTeamMember(@RequestBody ContestOrderNumDto param) {
		return success(commodityService.getContestTeamMember(param));
	}
	
	/**
	 * 添加参赛队员
	 * @param param
	 * @return
	 */
	@ApiIgnore
	@PostMapping("/addContestTeamMember")
	public Result addContestTeamMember(@RequestBody List<ContestTeamMember> param) {
		commodityService.addContestTeamMember(param);
		return success();
	}
	
	@ApiIgnore
	@PostMapping("/updateContestTeamMember")
	public Result updateContestTeamMember(@RequestBody List<ContestTeamMember> param) {
		commodityService.updateContestTeamMember(param);
		return success();
	}
	
	@ApiOperation("根据属性码查询商品")
	@PostMapping("/listCommodityByPropertyCode")
	public Result listCommodityByPropertyCode(@RequestBody FlashSaleCommodityParamDto param) {
		return success(commodityService.listCommodityByProperty(param));
	}
	
	@ApiOperation("新增球队球员")
	@PostMapping("/addTeamMember")
	public Result addTeamMember(@RequestBody TeamMemberAddDto param,HttpServletRequest request) {
		return success(commodityService.addTeamMember(param,request.getHeader("authorization")));
	}
}
