package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.OrderFormFieldValueParamDto;
import com.mmm.weixin.vo.OrderFormFieldValue;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderFormFieldValueMapper {
    int deleteByPrimaryKey(Integer valueid);

    int insert(OrderFormFieldValue record);

    OrderFormFieldValue selectByPrimaryKey(Integer valueid);

    List<OrderFormFieldValue> selectAll();

    int updateByPrimaryKey(OrderFormFieldValue record);
    
    OrderFormFieldValue selectByCondition(OrderFormFieldValueParamDto param);
}