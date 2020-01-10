package com.mmm.weixin.dto.param;

public class UserSearchParam {
    private int Uid;
    private String username;

    public void setUid(int uid) {
        Uid = uid;
    }

    public int getUid() {
        return Uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
