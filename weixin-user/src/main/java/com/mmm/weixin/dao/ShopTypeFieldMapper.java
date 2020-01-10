package com.mmm.weixin.dao;

import com.mmm.weixin.vo.ShopTypeField;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ShopTypeFieldMapper {
    int deleteByPrimaryKey(Integer fieldid);

    int insert(ShopTypeField record);

    ShopTypeField selectByPrimaryKey(Integer fieldid);

    List<ShopTypeField> selectAll();

    int updateByPrimaryKey(ShopTypeField record);
}