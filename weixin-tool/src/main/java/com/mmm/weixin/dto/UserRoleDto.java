package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserRoleDto {
    @ApiModelProperty("用户ID")
    private int userID;
    @ApiModelProperty("用户角色")
    private int role;

    public int getRole() {
        return role;
    }

    public int getUserID() {
        return userID;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
