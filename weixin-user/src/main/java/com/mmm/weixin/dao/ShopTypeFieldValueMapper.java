package com.mmm.weixin.dao;

import com.mmm.weixin.vo.ShopTypeFieldValue;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopTypeFieldValueMapper {
    int deleteByPrimaryKey(Integer valueid);

    int insert(ShopTypeFieldValue record);

    ShopTypeFieldValue selectByPrimaryKey(Integer valueid);

    List<ShopTypeFieldValue> selectAll();

    int updateByPrimaryKey(ShopTypeFieldValue record);
}