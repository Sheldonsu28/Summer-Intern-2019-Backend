package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.servlet.http.HttpServletRequest;
@ApiModel
public class UserSearchDto {
    @ApiModelProperty("Http请求")
    private HttpServletRequest request;
    @ApiModelProperty("想查询的用户名，可以不填")
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
