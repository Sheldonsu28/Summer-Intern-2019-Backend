package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.MatchAreaQueryParamDto;
import com.mmm.weixin.vo.GolfArea;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GolfAreaMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(GolfArea record);

    GolfArea selectByPrimaryKey(Integer aid);

    List<GolfArea> selectAll();

    int updateByPrimaryKey(GolfArea record);
    
    List<GolfArea> selectByCondition(MatchAreaQueryParamDto param);
}