package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.TeamMemberParamDto;
import com.mmm.weixin.vo.TeamMember;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamMemberMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(TeamMember record);

    TeamMember selectByPrimaryKey(Integer memberId);

    List<TeamMember> selectAll();

    int updateByPrimaryKey(TeamMember record);
    
    List<TeamMember> selectByCondition(TeamMemberParamDto param);
    
}