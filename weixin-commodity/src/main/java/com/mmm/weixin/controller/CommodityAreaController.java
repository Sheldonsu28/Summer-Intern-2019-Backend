package com.mmm.weixin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.param.AreaDataParamDto;
import com.mmm.weixin.dto.param.AreaNameParamDto;
import com.mmm.weixin.dto.param.AreaParentDataParamDto;
import com.mmm.weixin.service.IAreaDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="Commodity items")
@RestController
public class CommodityAreaController extends BaseController{
	
	@Autowired
	private IAreaDataService areaService;
	
	@Value("${province_parentId}")
	private Integer p_parentId;
	
	@ApiOperation("Get hotspot city")
	@GetMapping("/getHotArea")
	public Result getHotArea(){
		AreaDataParamDto param = new AreaDataParamDto();
		param.setIsHot(true);
		return success(areaService.getAreaDataByCondition(param));
	}
	
	@ApiOperation("Get province")
	@GetMapping("/getProvinces")
	public Result getProvince(){
		AreaDataParamDto param = new AreaDataParamDto();
		param.setParentId(p_parentId);
		return success(areaService.getAreaDataByCondition(param));
	}
	
	@ApiOperation("Get city in designated provinces")
	@PostMapping("/getAreasByPid")
	public Result getAreaByParentId(@RequestBody AreaParentDataParamDto param){
		AreaDataParamDto areaParam = new AreaDataParamDto();
		areaParam.setParentId(param.getParentId());
		return success(areaService.getAreaDataByCondition(areaParam));
	}
	
	@ApiOperation("City look up")
	@PostMapping("/getAreasByName")
	public Result getAreaByName(@RequestBody AreaNameParamDto param){
		AreaDataParamDto areaParam = new AreaDataParamDto();
		areaParam.setAreaName(param.getAreaName());
		areaParam.setIsFuzzy(1);
		return success(areaService.getAreaDataByCondition(areaParam));
	}
}
