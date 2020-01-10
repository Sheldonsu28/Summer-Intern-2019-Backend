package com.mmm.weixin.dao;

import com.mmm.weixin.vo.UserCharge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserChargeMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(UserCharge record);

    UserCharge selectByPrimaryKey(Integer cid);

    List<UserCharge> selectAll();

    int updateByPrimaryKey(UserCharge record);
}