package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.AreaDataParamDto;
import com.mmm.weixin.vo.AreaData;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AreaDataMapper {
    int deleteByPrimaryKey(Integer areaid);

    int insert(AreaData record);

    AreaData selectByPrimaryKey(Integer areaid);

    List<AreaData> selectAll();

    int updateByPrimaryKey(AreaData record);
    
    List<AreaData> selectByCondition(AreaDataParamDto param);
}