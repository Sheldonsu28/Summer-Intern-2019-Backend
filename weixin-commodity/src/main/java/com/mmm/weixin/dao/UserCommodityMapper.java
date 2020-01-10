package com.mmm.weixin.dao;

import com.mmm.weixin.vo.UserCommodity;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCommodityMapper {
    int insert(UserCommodity record);

    List<UserCommodity> selectAll();
    
    UserCommodity selectByCondition(UserCommodity userCommodity);
    
    void deleteByCondition(UserCommodity userCommodity);
}