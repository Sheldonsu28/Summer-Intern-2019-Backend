package com.mmm.weixin.dao;

import com.mmm.weixin.vo.AppointmentCommentReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AppointmentCommentReplyMapper {
    int deleteByPrimaryKey(Integer arid);

    int insert(AppointmentCommentReply record);

    AppointmentCommentReply selectByPrimaryKey(Integer arid);

    List<AppointmentCommentReply> selectAll();

    int updateByPrimaryKey(AppointmentCommentReply record);
    List<AppointmentCommentReply> selectByCommentID(Integer commentId);
    int updateToInvisibleByPrimaryKey(Integer arid);
}