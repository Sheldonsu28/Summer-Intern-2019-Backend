package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.TeamInfoParamDto;
import com.mmm.weixin.vo.TeamInfo;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamInfoMapper {
    int deleteByPrimaryKey(Integer teamId);

    int insert(TeamInfo record);

    TeamInfo selectByPrimaryKey(Integer teamId);

    List<TeamInfo> selectAll();

    int updateByPrimaryKey(TeamInfo record);
    
    List<TeamInfo> selectByCondition(TeamInfoParamDto param);
    
    TeamInfo selectByNameAndId(TeamInfoParamDto param);
}