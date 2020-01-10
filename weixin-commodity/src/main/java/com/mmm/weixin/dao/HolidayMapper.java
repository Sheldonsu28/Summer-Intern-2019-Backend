package com.mmm.weixin.dao;

import com.mmm.weixin.vo.Holiday;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HolidayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Holiday record);

    Holiday selectByPrimaryKey(Integer id);

    List<Holiday> selectAll();

    int updateByPrimaryKey(Holiday record);
    
    Holiday selectByHoliday(Holiday param);
}