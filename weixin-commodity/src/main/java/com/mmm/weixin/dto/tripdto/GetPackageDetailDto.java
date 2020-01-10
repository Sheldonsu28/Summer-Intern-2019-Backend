package com.mmm.weixin.dto.tripdto;

import io.swagger.annotations.ApiModelProperty;

public class GetPackageDetailDto {

    @ApiModelProperty("套餐ID")
    private Integer commodityId;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }
}
