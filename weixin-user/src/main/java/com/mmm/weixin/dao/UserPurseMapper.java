package com.mmm.weixin.dao;

import com.mmm.weixin.vo.UserPurse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserPurseMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(UserPurse record);

    UserPurse selectByPrimaryKey(Integer pid);

    UserPurse selectByUid(Integer uid);

    List<UserPurse> selectAll();

    int updateByPrimaryKey(UserPurse record);

    int updateBalanceByPid(UserPurse record);
}