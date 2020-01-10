package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel
public class MatchResultDto {
    @ApiModelProperty("分数结果")
    private int result;
    @ApiModelProperty("用户ID")
    private int userId;

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String toString(){
        return String.format("%d: %d",this.userId,this.result);
    }
}
