package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.CommentReplyParamDto;
import com.mmm.weixin.vo.CommentsReply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentsReplyMapper {
    int deleteByPrimaryKey(Integer replyId);

    int insert(CommentsReply record);

    CommentsReply selectByPrimaryKey(Integer replyId);

    List<CommentsReply> selectAll();

    int updateByPrimaryKey(CommentsReply record);
    
    List<CommentsReply> selectByCondition(CommentReplyParamDto param);
}