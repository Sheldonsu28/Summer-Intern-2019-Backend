package com.mmm.weixin.dao;

import com.mmm.weixin.vo.AppointmentImages;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.ApplicationTemp;

@Mapper
public interface AppointmentImagesMapper {
    int deleteByPrimaryKey(Integer aiId);

    int insert(AppointmentImages record);

    AppointmentImages selectByPrimaryKey(Integer aiId);

    List<AppointmentImages> selectAll();

    int updateByPrimaryKey(AppointmentImages record);
    
    int insertByList(List<AppointmentImages> list);
    
    List<AppointmentImages> selectByAid(Integer aid);
    
    AppointmentImages selectMainPicByAid(Integer aid);
}
