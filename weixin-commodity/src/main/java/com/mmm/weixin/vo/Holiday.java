package com.mmm.weixin.vo;

public class Holiday {
    private Integer id;

    private String holiday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday == null ? null : holiday.trim();
    }
}