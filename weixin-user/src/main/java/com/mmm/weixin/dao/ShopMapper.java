package com.mmm.weixin.dao;

import com.mmm.weixin.vo.Shop;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {
    int deleteByPrimaryKey(Integer shopid);

    int insert(Shop record);

    Shop selectByPrimaryKey(Integer shopid);

    List<Shop> selectAll();

    int updateByPrimaryKey(Shop record);
}