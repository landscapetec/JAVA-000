package com.lsf.studymybatis.entity;


import lombok.Getter;
import lombok.Setter;

public enum UserInfoValidateType implements BaseEnum<UserInfoValidateType> {
    NONE("000", "未知"),
    WECHAT("001", "微信"),
    MAIL("002", "邮箱");

    private String code;
    private String description;

    UserInfoValidateType(String code, String description) {
        this.code = code;
        this.description = description;
    }


    @Override
    public String getCode() {
        return code;
    }

}
