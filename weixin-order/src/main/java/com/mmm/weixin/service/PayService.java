package com.mmm.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.mmm.weixin.dto.param.OrderDto;
import com.mmm.weixin.pay.SignInfo;
import com.mmm.weixin.vo.PayVo;

import javax.servlet.http.HttpServletRequest;

public interface PayService {


    JSONObject payOrderUnion(PayVo payVo);

    String payResult(HttpServletRequest request);

    Boolean getPayResult(OrderDto dto);
}
