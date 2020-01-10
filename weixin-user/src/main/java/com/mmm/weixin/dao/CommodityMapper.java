package com.mmm.weixin.dao;

import com.mmm.weixin.vo.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommodityMapper {
    int deleteByPrimaryKey(Integer commodityid);

    int insert(Commodity record);

    Commodity selectByPrimaryKey(Integer commodityid);

    List<Commodity> selectAll();

    int updateByPrimaryKey(Commodity record);
}