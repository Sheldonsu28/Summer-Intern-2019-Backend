package com.mmm.weixin.dao;

import com.mmm.weixin.vo.OrderForm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TripOrderMapper {

    void SaveTripOrderMain(OrderForm orderForm);


}
