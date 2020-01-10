package com.mmm.weixin.dao;

import com.mmm.weixin.vo.ShopTypeField;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopTypeFieldMapper {
    int deleteByPrimaryKey(Integer fieldId);

    int insert(ShopTypeField record);

    ShopTypeField selectByPrimaryKey(Integer fieldId);

    List<ShopTypeField> selectAll();

    int updateByPrimaryKey(ShopTypeField record);
}