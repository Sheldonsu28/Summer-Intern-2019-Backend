package com.mmm.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.GolfShopParamDto;
import com.mmm.weixin.dto.MatchShopParamDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.ShopCommentQueryDto;
import com.mmm.weixin.dto.ShopDetailParamDto;
import com.mmm.weixin.dto.param.CommentParamDto;
import com.mmm.weixin.dto.param.CommentReplyParamDto;
import com.mmm.weixin.service.IShopService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(description="商家")
@RestController
public class ShopController extends BaseController{

	@Autowired
	private IShopService shopService;
	
	@ApiOperation("商家详情")
	@PostMapping("/getShopDetail")
	public Result getShopDetail(@RequestBody ShopDetailParamDto param){
		return success(shopService.getShopDetail(param));
	}
	
	@ApiOperation("查询商家评论")
	@PostMapping("/getCommentByShopId")
	public Result getCommentByShopId(@RequestBody ShopCommentQueryDto shopCommentQueryDto){
		return success(shopService.listCommentDtoByShopId(shopCommentQueryDto));
	}
	
	/*
	 * @ApiOperation("查询商家回复")
	 * 
	 * @PostMapping("/getReplyByCommentId") public Result
	 * getCommentReplyByCommentId(@RequestBody Integer commentId){
	 * CommentReplyParamDto param = new CommentReplyParamDto();
	 * param.setCommentId(commentId); return
	 * success(shopService.listCommentReplyByCondition(param)); }
	 */
	
	@ApiOperation("根据ID查询商家")
	@PostMapping("/getShopById")
	public Result getShopById(@RequestBody ShopDetailParamDto param) {
		return success(shopService.getShopById(param));
	}
	
	@ApiOperation("查询球场预定列表")
	@PostMapping("/getGolfShopList")
	public Result getGolfShopList(@RequestBody GolfShopParamDto param) {
		return success(shopService.listGolfCommodityByCondition(param));
	}
	
	@ApiIgnore
	@PostMapping("/getMatchShopList")
	public Result getMatchShopList(@RequestBody MatchShopParamDto param) {
		return success(shopService.listMatchShopByCondition(param));
	}
	
}
