package com.mmm.weixin.dao;

import com.mmm.weixin.vo.OrderFormField;
import java.util.List;

public interface OrderFormFieldMapper {
    int deleteByPrimaryKey(Integer fieldid);

    int insert(OrderFormField record);

    OrderFormField selectByPrimaryKey(Integer fieldid);

    List<OrderFormField> selectAll();

    int updateByPrimaryKey(OrderFormField record);
}