package com.mmm.weixin.dao;

import com.mmm.weixin.vo.Feedback;
import java.util.List;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer feedbackid);

    int insert(Feedback record);

    Feedback selectByPrimaryKey(Integer feedbackid);

    List<Feedback> selectAll();

    int updateByPrimaryKey(Feedback record);
}