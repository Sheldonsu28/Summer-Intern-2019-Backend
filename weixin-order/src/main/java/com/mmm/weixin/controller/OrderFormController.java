package com.mmm.weixin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.mmm.weixin.dto.param.OrderDto;
import com.mmm.weixin.dto.param.SaveTripOrderDto;
import com.mmm.weixin.dto.param.UserOrderListDto;
import com.mmm.weixin.service.TripOrderService;
import com.mmm.weixin.vo.DetailVo;
import com.mmm.weixin.vo.OrderForm;
import com.mmm.weixin.vo.OrderFormCustom;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.BaseDto;
import com.mmm.weixin.dto.ContestOrderNumDto;
import com.mmm.weixin.dto.ContestOrderParamDto;
import com.mmm.weixin.dto.GolfOrderParamDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.UpdateContestOrderParamDto;
import com.mmm.weixin.service.OrderFormService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiOperation;

@RestController
public class OrderFormController extends BaseController {

    //熔断提示信息,在weixin-common的resources-application-common.yml
    @Value("${hystrix_error}")
    private String hystrixError;

    @Autowired
    private OrderFormService orderService;

    @Autowired
    private TripOrderService tripOrderService;

    @HystrixCommand(fallbackMethod = "listFallback")
    @ApiOperation("查询订单列表")
    @PostMapping("/list")
    //@ApiImplicitParams({@ApiImplicitParam(name="currentPage",dataType="Integer",value="当前页"),@ApiImplicitParam(name="pageSize",dataType="Integer",value="每页数量")})
    public Result list(@Valid @RequestBody BaseDto baseDto) {
        return success(orderService.list(baseDto));
    }

    @ApiOperation("获取用户订单列表")
    @PostMapping("/findUserOrderList")
    public Result findUserOrderList(@RequestBody UserOrderListDto dto,HttpServletRequest request) {
        return success(orderService.findUserOrderList(dto,request.getHeader("authorization")));
    }

    @ApiOperation("获取订单详情")
    @PostMapping("/getOrderDetailById")
    public ResponseEntity<DetailVo> getOrderDetailById(@RequestBody OrderDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderInfoById(dto));
    }

    @ApiOperation("取消订单")
    @PostMapping("/cancelOrderById")
    public Result cancelOrderById(@RequestBody OrderDto dto) {
        orderService.cancelOrderById(dto);
        return success();
    }

    @ApiOperation("删除订单")
    @PostMapping("/deleteOrderById")
    public Result deleteOrderById(@RequestBody OrderDto dto) {
        orderService.deleteOrderById(dto);
        return success();
    }

    @ApiOperation("新增订单")
    @PostMapping("/addGolfOrder")
    @ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
    public Result addGolfOrder(@RequestBody GolfOrderParamDto orderparamdto, HttpServletRequest request) {
        orderService.addGolfOrder(orderparamdto, request.getHeader("authorization"));
        return success();
    }

    @ApiOperation("新增赛事订单")
    @PostMapping("/addContestOrder")
    @ApiImplicitParam(name = "authorization", dataType = "String", paramType = "query", required = true)
    public Result addContestOrder(@RequestBody ContestOrderParamDto param, HttpServletRequest request) {
        return success(orderService.addContestOrder(param, request.getHeader("authorization")));
    }

    @ApiOperation("修改团队赛事订单")
    @PostMapping("/updateContestOrder")
    public Result updateContestOrder(@RequestBody UpdateContestOrderParamDto param, HttpServletRequest request) {
        orderService.updateContestOrder(param, request.getHeader("authorization"));
        return success();
    }


    @ApiModelProperty("保存旅游订单信息")
    @PostMapping("/saveTripOrder")
    public ResponseEntity<OrderForm> saveTripOrder(@RequestBody SaveTripOrderDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(tripOrderService.saveTripOrder(dto));
    }

    @ApiOperation("获取团队赛参赛队员")
    @PostMapping("/getContestMember")
    public Result getContestMember(@RequestBody ContestOrderNumDto param, HttpServletRequest request) {
        return success(orderService.getContestMember(param, request.getHeader("authorization")));
    }
}
