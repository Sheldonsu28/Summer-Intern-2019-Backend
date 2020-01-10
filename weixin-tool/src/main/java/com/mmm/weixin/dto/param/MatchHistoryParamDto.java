package com.mmm.weixin.dto.param;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.annotations.ApiIgnore;

@ApiModel
public class MatchHistoryParamDto {
    @ApiModelProperty("你想查询的用户名,可以不填")
    private String username;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
