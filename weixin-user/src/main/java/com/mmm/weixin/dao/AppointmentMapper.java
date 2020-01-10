package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.AppointmentQueryDto;
import com.mmm.weixin.vo.Appointment;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(Appointment record);

    Appointment selectByPrimaryKey(Integer aid);

    List<Appointment> selectAll();

    int updateByPrimaryKey(Appointment record);
    
    List<Appointment> selectByCondition(AppointmentQueryDto param);
    
    List<Appointment> selectByComment(AppointmentQueryDto param);
}
