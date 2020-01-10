package com.mmm.weixin.dao;

import com.mmm.weixin.vo.OrderFormPay;
import java.util.List;

public interface OrderFormPayMapper {
    int deleteByPrimaryKey(Integer payid);

    int insert(OrderFormPay record);

    OrderFormPay selectByPrimaryKey(Integer payid);

    List<OrderFormPay> selectAll();

    int updateByPrimaryKey(OrderFormPay record);
}