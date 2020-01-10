package com.mmm.weixin.dao;

import com.mmm.weixin.vo.CommoditySku;
import java.util.List;

public interface CommoditySkuMapper {
    int deleteByPrimaryKey(Integer skuid);

    int insert(CommoditySku record);

    CommoditySku selectByPrimaryKey(Integer skuid);

    List<CommoditySku> selectAll();

    int updateByPrimaryKey(CommoditySku record);
}