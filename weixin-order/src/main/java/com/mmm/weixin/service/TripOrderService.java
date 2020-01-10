package com.mmm.weixin.service;

import com.mmm.weixin.dto.param.SaveTripOrderDto;
import com.mmm.weixin.vo.OrderForm;

public interface TripOrderService {

    OrderForm saveTripOrder(SaveTripOrderDto dto);
}
