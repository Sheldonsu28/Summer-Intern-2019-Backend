package com.mmm.weixin.service;

import com.github.pagehelper.PageInfo;
import com.mmm.weixin.dto.tripdto.*;
import com.mmm.weixin.vo.AdInfo;
import com.mmm.weixin.vo.tripvo.FindTripPackageListVo;
import com.mmm.weixin.vo.tripvo.GetPackageDetailVo;
import com.mmm.weixin.vo.tripvo.SingleTripListVo;

import java.util.List;


public interface TripCommodityService {

    PageInfo<FindTripPackageListVo> findTripPackageList(FindTripPackageListDto dto);

    GetPackageDetailVo getPackageDetailById(GetPackageDetailDto dto);

    List<AdInfo> findAdInfoList(FindAdInfoDto dto);

    PageInfo<SingleTripListVo> findSingleTripList(SingleTripListDto dto);

    SingleTripListVo getSingleTripDetail(SingleTripDetailDto dto);
}
