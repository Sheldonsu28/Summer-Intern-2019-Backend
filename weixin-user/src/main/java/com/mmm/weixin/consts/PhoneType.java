package com.mmm.weixin.consts;

public enum PhoneType {

    USER_REGISTER_TYPE(1, "用户注册短信类型"),
    USER_UPDATETEL_TYPE(2, "用户修改手机短信类型"),
    ORDER_SUCCESS_TYPE(3, "下单通知短信类型");

    private Integer type;
    private String description;

    private PhoneType(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public static PhoneType typeOf(Integer value) {

        switch (value) {
            case 1: {
                return USER_REGISTER_TYPE;
            }
            case 2: {
                return USER_UPDATETEL_TYPE;
            }
            case 3: {
                return ORDER_SUCCESS_TYPE;
            }
            default: {
                return null;
            }
        }
    }

}
