package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class UserOrderListDto {

    @ApiModelProperty("当前页(非必传, 默认1)")
    private Integer currentPage = 1;
    @ApiModelProperty("每页条数(非必传, 默认10)")
    private Integer pageSize = 10;
    @ApiModelProperty("用户token")
    private String authorization;
    @ApiModelProperty("订单状态(-2全部-1.已退款 0.已取消 1.待确认 2.待付款 3.待使用 4.待评价)")
    private Integer stateCode;
    @ApiModelProperty("订单类型(1.高尔夫球场订单 2.旅游套餐订单 3.团队赛订单)")
    private Integer orderType;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
