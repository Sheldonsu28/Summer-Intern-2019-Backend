package com.mmm.weixin.controller;

import com.github.pagehelper.PageInfo;
import com.mmm.weixin.dto.tripdto.SingleTripDetailDto;
import com.mmm.weixin.dto.tripdto.SingleTripListDto;
import com.mmm.weixin.service.TripCommodityService;
import com.mmm.weixin.vo.tripvo.SingleTripListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Api(description = "单独旅游模块")
@RestController
public class SingleTripController {

    @Autowired
    private TripCommodityService tripCommodityService;

    @ApiOperation("获取旅游列表")
    @PostMapping("/findSingleTripList")
    public ResponseEntity<PageInfo<SingleTripListVo>> findSingleTripList(@RequestBody SingleTripListDto dto) {
        return ResponseEntity.ok(tripCommodityService.findSingleTripList(dto));
    }

    @ApiOperation("获取旅游商品图文详情")
    @PostMapping("/getSingleTripDetail")
    public ResponseEntity<SingleTripListVo> getSingleTripDetail(@RequestBody SingleTripDetailDto dto) {
        return ResponseEntity.ok(tripCommodityService.getSingleTripDetail(dto));
    }
}
