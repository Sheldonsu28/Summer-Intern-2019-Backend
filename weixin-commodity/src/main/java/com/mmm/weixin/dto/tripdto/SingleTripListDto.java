package com.mmm.weixin.dto.tripdto;

import io.swagger.annotations.ApiModelProperty;

public class SingleTripListDto {

    @ApiModelProperty("当前页(非比传, 默认1)")
    private Integer currentPage = 1;
    @ApiModelProperty("每页条数(非必传, 默认10)")
    private Integer pageSize = 10;
    @ApiModelProperty("搜索关键字")
    private String keyword;

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
