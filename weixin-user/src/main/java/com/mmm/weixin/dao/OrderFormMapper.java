package com.mmm.weixin.dao;

import com.mmm.weixin.vo.OrderForm;
import java.util.List;

public interface OrderFormMapper {
    int deleteByPrimaryKey(Integer orderformid);

    int insert(OrderForm record);

    OrderForm selectByPrimaryKey(Integer orderformid);

    List<OrderForm> selectAll();

    int updateByPrimaryKey(OrderForm record);
}