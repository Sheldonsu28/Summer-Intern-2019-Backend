package com.mmm.weixin.dao;

import com.mmm.weixin.dto.CommentDto;
import com.mmm.weixin.dto.param.CommentParamDto;
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
    
    List<CommentDto> selectCommentDtoByCondition(CommentParamDto param);
}