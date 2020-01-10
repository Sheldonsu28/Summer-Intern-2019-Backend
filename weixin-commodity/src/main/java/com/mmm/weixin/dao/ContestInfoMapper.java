package com.mmm.weixin.dao;

import com.mmm.weixin.dto.ContestInfoParamDto;
import com.mmm.weixin.vo.ContestInfo;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ContestInfoMapper {
    int deleteByPrimaryKey(Integer contestId);

    int insert(ContestInfo record);

    ContestInfo selectByPrimaryKey(Integer contestId);

    List<ContestInfo> selectAll();

    int updateByPrimaryKey(ContestInfo record);
    
    List<ContestInfo> selectByCondition(ContestInfoParamDto param);
}