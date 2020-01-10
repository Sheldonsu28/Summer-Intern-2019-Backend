package com.mmm.weixin.dao;

import com.mmm.weixin.vo.OrderFormPay;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderFormPayMapper {
    int insert(OrderFormPay record);

    List<OrderFormPay> selectAll();
}