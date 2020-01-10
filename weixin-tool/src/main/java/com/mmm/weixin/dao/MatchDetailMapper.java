package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.MatchDetailParamDto;
import com.mmm.weixin.vo.MatchDetail;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MatchDetailMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(MatchDetail record);

    MatchDetail selectByPrimaryKey(Integer did);

    List<MatchDetail> selectAll();

    int updateByPrimaryKey(MatchDetail record);
    
    List<MatchDetail> selectByCondition(MatchDetailParamDto param);
    
    List<MatchDetail> selectMatchArea(Integer mid);
    int createDetail(MatchDetail detail);
}