package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.ShopImageParamDto;
import com.mmm.weixin.vo.ShopImage;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopImageMapper {
    int deleteByPrimaryKey(Integer imgId);

    int insert(ShopImage record);

    ShopImage selectByPrimaryKey(Integer imgId);

    List<ShopImage> selectAll();

    int updateByPrimaryKey(ShopImage record);
    
    List<ShopImage> selectByCondition(ShopImageParamDto param);
    
}