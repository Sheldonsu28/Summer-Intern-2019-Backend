package com.mmm.weixin.dao;

import com.mmm.weixin.vo.CommodityImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommodityImageMapper {
    int deleteByPrimaryKey(Integer imgid);

    int insert(CommodityImage record);

    CommodityImage selectByPrimaryKey(Integer imgid);

    List<CommodityImage> selectAll();

    int updateByPrimaryKey(CommodityImage record);
}