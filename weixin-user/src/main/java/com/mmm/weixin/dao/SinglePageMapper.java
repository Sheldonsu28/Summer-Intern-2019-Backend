package com.mmm.weixin.dao;

import com.mmm.weixin.vo.SinglePage;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SinglePageMapper {
    int deleteByPrimaryKey(Integer pageid);

    int insert(SinglePage record);

    SinglePage selectByPrimaryKey(Integer pageid);

    List<SinglePage> selectAll();

    int updateByPrimaryKey(SinglePage record);
}