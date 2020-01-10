package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.ContestTeamMemberParamDto;
import com.mmm.weixin.vo.ContestTeamMember;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestTeamMemberMapper {
    int deleteByPrimaryKey(Integer ctmId);

    int insert(ContestTeamMember record);

    ContestTeamMember selectByPrimaryKey(Integer ctmId);

    List<ContestTeamMember> selectAll();

    int updateByPrimaryKey(ContestTeamMember record);
    
    List<ContestTeamMember> selectByCondition(ContestTeamMemberParamDto param);
}