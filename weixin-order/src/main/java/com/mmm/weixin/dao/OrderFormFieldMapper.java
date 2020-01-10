package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.OrderFormFieldParamDto;
import com.mmm.weixin.vo.OrderFormField;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderFormFieldMapper {
    int deleteByPrimaryKey(Integer fieldid);

    int insert(OrderFormField record);

    OrderFormField selectByPrimaryKey(Integer fieldid);

    List<OrderFormField> selectAll();

    int updateByPrimaryKey(OrderFormField record);
    
    List<OrderFormField> selectByOderType(Integer orderFormType);
    
    OrderFormField selectByCondition(OrderFormFieldParamDto param);
}