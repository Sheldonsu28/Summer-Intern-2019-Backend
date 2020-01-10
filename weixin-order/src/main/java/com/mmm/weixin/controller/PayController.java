package com.mmm.weixin.controller;

import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.param.OrderDto;
import com.mmm.weixin.service.PayService;
import com.mmm.weixin.vo.PayVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PayController extends BaseController {

    @Autowired
    private PayService payService;

    @ApiOperation("统一下单接口")
    @PostMapping("/payOrderUnion")
    public Result payOrderUnion(@RequestBody PayVo payVo) {
        return success(payService.payOrderUnion(payVo));
    }

    @PostMapping("/payResult")
    public String payResult(HttpServletRequest request) {
        return payService.payResult(request);
    }

    @ApiOperation("支付结果查询")
    @PostMapping("/getPayResult")
    public Result getPayResult(@RequestBody OrderDto dto) {
        return success(payService.getPayResult(dto));
    }
}
