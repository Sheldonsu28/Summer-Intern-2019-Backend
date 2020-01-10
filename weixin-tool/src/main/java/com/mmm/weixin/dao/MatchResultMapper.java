package com.mmm.weixin.dao;

import com.mmm.weixin.dto.SinglePlayerResultDto;
import com.mmm.weixin.dto.param.MatchPlayerParamDto;
import com.mmm.weixin.dto.param.MatchResultParamDto;
import com.mmm.weixin.vo.MatchResult;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MatchResultMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(MatchResult record);

    MatchResult selectByPrimaryKey(Integer rid);

    List<MatchResult> selectAll();

    int updateByPrimaryKey(MatchResult record);
    
    List<MatchResult> selectByMidAndUserId(MatchResultParamDto param);
    MatchResult selectByParameter(MatchResult paramDto);
    int updateByRid(MatchResult result);
    int createResult(MatchResult result);
    
    SinglePlayerResultDto selectPlayerResult(MatchPlayerParamDto param);
}