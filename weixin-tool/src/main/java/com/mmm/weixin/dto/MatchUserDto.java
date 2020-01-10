package com.mmm.weixin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MatchUserDto {
    @ApiModelProperty("比赛用户ID")
    private int ID;
    @ApiModelProperty("用户名字")
    private String Name;
    @ApiModelProperty("用户头像url")
    private String thumbnailUrl;
    @ApiModelProperty("用户参与过的比赛次数")
    private int matchHistory;

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setThumbnailUrl(String url){
        this.thumbnailUrl = url;
    }

    public void setMatchHistory(int number){
        this.matchHistory = number;
    }

    public String getThumbnailUrl(){
        return this.thumbnailUrl;
    }

    public int getMatchHistory(){
        return this.matchHistory;
    }
}
