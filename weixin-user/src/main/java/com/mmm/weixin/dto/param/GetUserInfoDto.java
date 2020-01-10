package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModelProperty;

public class GetUserInfoDto {
    @ApiModelProperty("当前页(必传)")
    private Integer currentPage;
    @ApiModelProperty("每页条数(非必传)")
    private Integer pageSize = 10;

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
}
