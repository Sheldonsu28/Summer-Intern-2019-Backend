package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.MatchHistoryParamDto;
import com.mmm.weixin.dto.param.MatchPlayerParamDto;
import com.mmm.weixin.vo.MatchPlayer;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MatchPlayerMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(MatchPlayer record);

    MatchPlayer selectByPrimaryKey(Integer pid);

    List<MatchPlayer> selectAll();

    int updateByPrimaryKey(MatchPlayer record);
    
    List<MatchPlayer> selectByUserId(Integer userId);
    
    List<MatchPlayer> selectByMatchID(MatchPlayerParamDto param);
    
    List<MatchPlayer> selectByCondition(MatchPlayerParamDto param);

    int updateByParam(MatchPlayer player);
    MatchPlayer selectByMidAndUidAndIsQuit(MatchPlayer player);
    List<MatchPlayer> selectByMidAndIsQuit(int mid);
    int updateIsQuitToTrueByUserId(MatchPlayer player);
    int createPlayer(MatchPlayer player);
    List<MatchPlayer> selectPlayersByMatchIdAndIsQuit(int mid);
    MatchPlayer selectByMidAndUid(MatchPlayer matchPlayer);
    int updateIsQuitToFalseByUserId(MatchPlayer matchPlayer);
    List<MatchPlayer> selectHistoryPlayer(int uid);
}
