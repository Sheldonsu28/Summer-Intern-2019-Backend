package com.mmm.weixin.dao;

import com.mmm.weixin.dto.CommentParamDto;
import com.mmm.weixin.vo.Comments;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentsMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comments record);

    Comments selectByPrimaryKey(Integer commentId);

    List<Comments> selectAll();

    int updateByPrimaryKey(Comments record);
    
    List<Comments> selectByCondition(CommentParamDto param);
}