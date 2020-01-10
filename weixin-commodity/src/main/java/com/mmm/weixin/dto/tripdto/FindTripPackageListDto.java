package com.mmm.weixin.dto.tripdto;

import io.swagger.annotations.ApiModelProperty;

public class FindTripPackageListDto {

    @ApiModelProperty("当前页(非比传, 默认1)")
    private Integer currentPage = 1;
    @ApiModelProperty("每页条数(非必传, 默认10)")
    private Integer pageSize = 10;
    @ApiModelProperty("地区名称")
    private String areaName;

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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
