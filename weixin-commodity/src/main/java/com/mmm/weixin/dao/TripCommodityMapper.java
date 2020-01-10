package com.mmm.weixin.dao;

import com.mmm.weixin.dto.tripdto.FindAdInfoDto;
import com.mmm.weixin.dto.tripdto.GetPackageDetailDto;
import com.mmm.weixin.vo.AdInfo;
import com.mmm.weixin.vo.tripvo.FindTripPackageListVo;
import com.mmm.weixin.vo.tripvo.GetPackageDetailVo;
import com.mmm.weixin.vo.tripvo.SingleTripListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TripCommodityMapper {


    List<FindTripPackageListVo> findTripPackageList(String areaName);

    List<String> getProdImgs(Integer commodity);

    GetPackageDetailVo getPackageDetailById(GetPackageDetailDto dto);

    List<AdInfo> findAdInfoList(FindAdInfoDto dto);

    List<SingleTripListVo> findSingleTripList(String keyword);

    List<String> getCommodityImgs(@Param("commodityId") Integer commodityId, @Param("imgType") Integer imgType);

    SingleTripListVo getSingleTripDetail(Integer commodityId);
}
