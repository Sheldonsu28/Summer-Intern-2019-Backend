package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.CommodityImgParamDto;
import com.mmm.weixin.vo.CommodityImage;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommodityImageMapper {
    int deleteByPrimaryKey(Integer imgId);

    int insert(CommodityImage record);

    CommodityImage selectByPrimaryKey(Integer imgId);

    List<CommodityImage> selectAll();

    int updateByPrimaryKey(CommodityImage record);
    
    List<CommodityImage> selectByCondition(CommodityImgParamDto param);
}