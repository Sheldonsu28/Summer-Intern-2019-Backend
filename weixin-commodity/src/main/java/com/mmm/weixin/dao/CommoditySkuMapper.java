package com.mmm.weixin.dao;

import com.mmm.weixin.vo.CommoditySku;
import java.util.List;

public interface CommoditySkuMapper {
    int deleteByPrimaryKey(Integer SKUId);

    int insert(CommoditySku record);

    CommoditySku selectByPrimaryKey(Integer SKUId);

    List<CommoditySku> selectAll();

    int updateByPrimaryKey(CommoditySku record);
}