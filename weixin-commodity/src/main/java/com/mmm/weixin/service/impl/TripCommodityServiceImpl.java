package com.mmm.weixin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmm.weixin.dao.TripCommodityMapper;
import com.mmm.weixin.dto.tripdto.*;
import com.mmm.weixin.service.TripCommodityService;
import com.mmm.weixin.vo.AdInfo;
import com.mmm.weixin.vo.tripvo.FindTripPackageListVo;
import com.mmm.weixin.vo.tripvo.GetPackageDetailVo;
import com.mmm.weixin.vo.tripvo.SingleTripListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripCommodityServiceImpl implements TripCommodityService {

    private static final Logger logger = LoggerFactory.getLogger(TripCommodityServiceImpl.class);

    @Autowired
    private TripCommodityMapper tripCommodityMapper;

    @Override
    public PageInfo<FindTripPackageListVo> findTripPackageList(FindTripPackageListDto dto) {

        PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize());
        List<FindTripPackageListVo> list = tripCommodityMapper.findTripPackageList(dto.getAreaName());
        for (FindTripPackageListVo vo : list) {
            vo.setImgUrl(tripCommodityMapper.getProdImgs(vo.getCommodityId()));
        }

        return new PageInfo<>(list);
    }

    @Override
    public GetPackageDetailVo getPackageDetailById(GetPackageDetailDto dto) {

        GetPackageDetailVo vo = tripCommodityMapper.getPackageDetailById(dto);
        vo.setImgUrl(tripCommodityMapper.getProdImgs(dto.getCommodityId()));
        return vo;
    }

    @Override
    public List<AdInfo> findAdInfoList(FindAdInfoDto dto) {

        List<AdInfo> adInfoList = tripCommodityMapper.findAdInfoList(dto);
        return adInfoList;
    }

    @Override
    public PageInfo<SingleTripListVo> findSingleTripList(SingleTripListDto dto) {
        PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize());
        List<SingleTripListVo> list = tripCommodityMapper.findSingleTripList(dto.getKeyword());
        list.forEach(el -> {
            try {
                el.setImgUrls(tripCommodityMapper.getProdImgs(el.getCommodityId()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return new PageInfo<>(list);
    }

    @Override
    public SingleTripListVo getSingleTripDetail(SingleTripDetailDto dto) {

        logger.info("传入参数：" + dto);

        SingleTripListVo vo = tripCommodityMapper.getSingleTripDetail(dto.getCommodityId());
        if (vo == null) {
            throw new RuntimeException("请传递正确的旅游商品ID!");
        }
        vo.setImgUrls(tripCommodityMapper.getProdImgs(dto.getCommodityId()));
        List<String> commodityImgs = tripCommodityMapper.getCommodityImgs(dto.getCommodityId(), 3);
        vo.setDetailImgUrls(tripCommodityMapper.getCommodityImgs(dto.getCommodityId(), 3));

        return vo;
    }

}
