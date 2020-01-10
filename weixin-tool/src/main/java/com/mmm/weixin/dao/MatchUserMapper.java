package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.MatchHistoryParamDto;
import com.mmm.weixin.dto.param.UserSearchParam;
import com.mmm.weixin.vo.MatchUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MatchUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(MatchUser record);

    MatchUser selectByPrimaryKey(UserSearchParam searchParam);

    List<MatchUser> selectAll();

    int updateByPrimaryKey(MatchUser record);
}
