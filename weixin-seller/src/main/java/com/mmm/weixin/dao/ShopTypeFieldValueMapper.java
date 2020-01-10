package com.mmm.weixin.dao;

import com.mmm.weixin.vo.ShopTypeFieldValue;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopTypeFieldValueMapper {
    int deleteByPrimaryKey(Integer valueId);

    int insert(ShopTypeFieldValue record);

    ShopTypeFieldValue selectByPrimaryKey(Integer valueId);

    List<ShopTypeFieldValue> selectAll();

    int updateByPrimaryKey(ShopTypeFieldValue record);
    
    List<ShopTypeFieldValue> selectByCondition(Integer fieldId);

}