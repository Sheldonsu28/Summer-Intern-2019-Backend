package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.AppointmentMsgParamDto;
import com.mmm.weixin.vo.AppointmentMessage;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentMessageMapper {
    int deleteByPrimaryKey(Integer amid);

    int insert(AppointmentMessage record);

    AppointmentMessage selectByPrimaryKey(Integer amid);

    List<AppointmentMessage> selectAll();

    int updateByPrimaryKey(AppointmentMessage record);
                   
    List<AppointmentMessage> selectByUid(AppointmentMsgParamDto param);
    
    int selectUnreadByUid(Integer uid);
}