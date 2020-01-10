package com.mmm.weixin.dao;

import com.mmm.weixin.dto.CommodityDto;
import com.mmm.weixin.dto.FlashSaleCommodityParamDto;
import com.mmm.weixin.dto.GolfShopParamDto;
import com.mmm.weixin.dto.param.CommodityImgParamDto;
import com.mmm.weixin.dto.param.CommodityParamDto;
import com.mmm.weixin.vo.Commodity;
import com.mmm.weixin.vo.CommodityImage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommodityMapper {
	int deleteByPrimaryKey(Integer commodityId);

    int insert(Commodity record);

    Commodity selectByPrimaryKey(Integer commodityId);

    List<Commodity> selectAll();

    int updateByPrimaryKey(Commodity record);
    
    List<Commodity> selectByCondition(CommodityParamDto param);
    
    List<CommodityDto> selectCommDtoByCondition(CommodityParamDto param);
    
    List<Commodity> selectDefaultCommodityByCondition(GolfShopParamDto param);
    
    List<CommodityImage> selectByCondition(CommodityImgParamDto param);
    
    List<Commodity> selectCommodityByProperty(FlashSaleCommodityParamDto param);
}