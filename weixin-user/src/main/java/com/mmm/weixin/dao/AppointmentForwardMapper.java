package com.mmm.weixin.dao;

import com.mmm.weixin.vo.AppointmentForward;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentForwardMapper {
    int deleteByPrimaryKey(Integer afid);

    int insert(AppointmentForward record);

    AppointmentForward selectByPrimaryKey(Integer afid);

    List<AppointmentForward> selectAll();

    int updateByPrimaryKey(AppointmentForward record);
}