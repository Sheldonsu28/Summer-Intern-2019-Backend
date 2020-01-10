package com.mmm.weixin.dao;

import com.mmm.weixin.vo.AreaHole;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AreaHoleMapper {
    int deleteByPrimaryKey(Integer hid);

    int insert(AreaHole record);

    AreaHole selectByPrimaryKey(Integer hid);

    List<AreaHole> selectAll();

    int updateByPrimaryKey(AreaHole record);
    
    List<AreaHole> selectByAid(Integer aid);

    List<AreaHole> selectByAidAndShopID(AreaHole areaHole);
}