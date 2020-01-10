package com.mmm.weixin.controller;

import com.github.pagehelper.PageInfo;
import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.tripdto.FindAdInfoDto;
import com.mmm.weixin.dto.tripdto.FindTripPackageListDto;
import com.mmm.weixin.dto.tripdto.GetPackageDetailDto;
import com.mmm.weixin.service.TripCommodityService;
import com.mmm.weixin.vo.AdInfo;
import com.mmm.weixin.vo.tripvo.FindTripPackageListVo;
import com.mmm.weixin.vo.tripvo.GetPackageDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "高球旅游套餐")
@RestController
public class TripCommodityController extends BaseController {

    @Autowired
    private TripCommodityService tripCommodityService;

    @ApiOperation("获取旅游套餐列表")
    @PostMapping("/findTripPackageList")
    public ResponseEntity<PageInfo<FindTripPackageListVo>> findTripPackageList(@RequestBody FindTripPackageListDto dto) {

        try {
            PageInfo<FindTripPackageListVo> tripPackageList = tripCommodityService.findTripPackageList(dto);
            return ResponseEntity.status(HttpStatus.OK).body(tripPackageList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation("获取套餐详情")
    @PostMapping("/getPackageDetailById")
    public ResponseEntity<GetPackageDetailVo> getPackageDetailById(@RequestBody GetPackageDetailDto dto) {

        try {
            GetPackageDetailVo vo = tripCommodityService.getPackageDetailById(dto);
            return ResponseEntity.status(HttpStatus.OK).body(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation("获取广告信息")
    @PostMapping("/findAdInfoList")
    public ResponseEntity<List<AdInfo>> findAdInfoList(@RequestBody FindAdInfoDto dto) {
        try {
            return ResponseEntity.ok(tripCommodityService.findAdInfoList(dto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
