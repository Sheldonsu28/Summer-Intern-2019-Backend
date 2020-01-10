package com.mmm.weixin.dao;

import com.mmm.weixin.vo.Message;
import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer messageid);

    int insert(Message record);

    Message selectByPrimaryKey(Integer messageid);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);
}