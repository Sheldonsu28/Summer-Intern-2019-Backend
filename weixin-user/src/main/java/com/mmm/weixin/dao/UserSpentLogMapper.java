package com.mmm.weixin.dao;

import com.mmm.weixin.vo.UserSpentLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserSpentLogMapper {
    int deleteByPrimaryKey(Integer lid);

    int insert(UserSpentLog record);

    UserSpentLog selectByPrimaryKey(Integer lid);

    List<UserSpentLog> selectAll();

    int updateByPrimaryKey(UserSpentLog record);

    int insertInterUserTransactionRecord(UserSpentLog record);
    int insertTransactionRecord(UserSpentLog record);
}