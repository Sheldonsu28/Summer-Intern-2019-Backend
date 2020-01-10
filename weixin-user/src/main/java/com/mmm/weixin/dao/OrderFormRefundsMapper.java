package com.mmm.weixin.dao;

import com.mmm.weixin.vo.OrderFormRefunds;
import java.util.List;

public interface OrderFormRefundsMapper {
    int deleteByPrimaryKey(Integer refundsid);

    int insert(OrderFormRefunds record);

    OrderFormRefunds selectByPrimaryKey(Integer refundsid);

    List<OrderFormRefunds> selectAll();

    int updateByPrimaryKey(OrderFormRefunds record);
}