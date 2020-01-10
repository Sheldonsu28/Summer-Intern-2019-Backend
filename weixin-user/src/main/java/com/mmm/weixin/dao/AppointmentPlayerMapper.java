package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.AppointmentPlayerQueryDto;
import com.mmm.weixin.vo.AppointmentPlayer;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentPlayerMapper {
    int deleteByPrimaryKey(Integer apid);

    int insert(AppointmentPlayer record);

    AppointmentPlayer selectByPrimaryKey(Integer apid);

    List<AppointmentPlayer> selectAll();

    int updateByPrimaryKey(AppointmentPlayer record);
    
    List<AppointmentPlayer> selectByCondition(AppointmentPlayerQueryDto param);
}