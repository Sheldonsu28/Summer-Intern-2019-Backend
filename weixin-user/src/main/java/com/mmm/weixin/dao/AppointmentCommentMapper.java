package com.mmm.weixin.dao;

import com.mmm.weixin.vo.AppointmentComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AppointmentCommentMapper {
    int deleteByPrimaryKey(Integer acid);

    int insert(AppointmentComment record);

    AppointmentComment selectByPrimaryKey(Integer acid);

    List<AppointmentComment> selectAll();

    int updateByPrimaryKey(AppointmentComment record);
    List<AppointmentComment> selectByAppointmentID(Integer appointmentID);
}