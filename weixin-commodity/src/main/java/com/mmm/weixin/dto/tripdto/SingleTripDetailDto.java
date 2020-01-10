package com.mmm.weixin.dto.tripdto;

import io.swagger.annotations.ApiModelProperty;

public class SingleTripDetailDto {

    @ApiModelProperty("商品ID")
    private Integer commodityId;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    public String toString() {
        return "SingleTripDetailDto{" +
                "commodityId=" + commodityId +
                '}';
    }
}
