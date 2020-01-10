package com.mmm.weixin.dao;

import com.mmm.weixin.vo.ContestTeamInfo;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestTeamInfoMapper {
    int deleteByPrimaryKey(Integer ctId);

    int insert(ContestTeamInfo record);

    ContestTeamInfo selectByPrimaryKey(Integer ctId);

    List<ContestTeamInfo> selectAll();

    int updateByPrimaryKey(ContestTeamInfo record);
    
    List<ContestTeamInfo> selectByCondition(ContestTeamInfo param);
}