package com.mmm.weixin.dto.tripdto;

import io.swagger.annotations.ApiModelProperty;

public class FindAdInfoDto {

    @ApiModelProperty("广告位置(1首页2高尔夫首页3高球旅游4旅游)")
    private Integer showLocation = 1;
    @ApiModelProperty("显示条数")
    private Integer showNum = 5;

    public Integer getShowLocation() {
        return showLocation;
    }

    public void setShowLocation(Integer showLocation) {
        this.showLocation = showLocation;
    }

    public Integer getShowNum() {
        return showNum;
    }

    public void setShowNum(Integer showNum) {
        this.showNum = showNum;
    }
}
