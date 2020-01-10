package com.mmm.weixin.dao;

import com.mmm.weixin.dto.MatchShopDto;
import com.mmm.weixin.dto.MatchShopParamDto;
import com.mmm.weixin.dto.param.PlayedMatchShopParamDto;
import com.mmm.weixin.vo.Shop;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(Shop record);

    Shop selectByPrimaryKey(Integer shopId);

    List<Shop> selectAll();

    int updateByPrimaryKey(Shop record);
    
    List<MatchShopDto> selectMatchShopByCondition(MatchShopParamDto param);
    
    List<MatchShopDto> selectPlayedMatchShop(PlayedMatchShopParamDto param);
}