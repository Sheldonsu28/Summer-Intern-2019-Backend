package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.UserMatchParamDto;
import com.mmm.weixin.vo.Match;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MatchMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Match record);

    Match selectByPrimaryKey(Integer mid);

    List<Match> selectAll();

    int updateByPrimaryKey(Match record);
    
    List<Match> selectByCondition(UserMatchParamDto param);
    
    int selectLatestMatchByUserId(int userId);
    
    List<Match> selectByUserID(int uid);
    
    List<Match> selectReadyMatch(String beginTime);

    int createMatch(Match match);
    int updateNameAndTimeByMid(Match match);
}