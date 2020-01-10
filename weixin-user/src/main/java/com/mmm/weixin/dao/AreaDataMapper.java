package com.mmm.weixin.dao;

import com.mmm.weixin.vo.AreaData;
import java.util.List;

public interface AreaDataMapper {
    int deleteByPrimaryKey(Integer areaid);

    int insert(AreaData record);

    AreaData selectByPrimaryKey(Integer areaid);

    List<AreaData> selectAll();

    int updateByPrimaryKey(AreaData record);
}