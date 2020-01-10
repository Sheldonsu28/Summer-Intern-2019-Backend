package com.mmm.weixin.dao;

import com.mmm.weixin.vo.OrderFormRefunds;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderFormRefundsMapper {
    int insert(OrderFormRefunds record);

    List<OrderFormRefunds> selectAll();
}