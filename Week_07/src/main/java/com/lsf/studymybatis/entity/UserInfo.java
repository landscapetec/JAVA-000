package com.lsf.studymybatis.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private long id;
    private long memberInfoId;
    private UserInfoValidateType validateType;
    private String validateInfo;
    private String validatePWD;
    private boolean deleted;
    private boolean activity;
    private Date bindTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMemberInfoId() {
        return memberInfoId;
    }

    public void setMemberInfoId(long memberInfoId) {
        this.memberInfoId = memberInfoId;
    }

    public UserInfoValidateType getValidateType() {
        return validateType;
    }

    public void setValidateType(UserInfoValidateType validateType) {
        this.validateType = validateType;
    }

    public String getValidateInfo() {
        return validateInfo;
    }

    public void setValidateInfo(String validateInfo) {
        this.validateInfo = validateInfo;
    }

    public String getValidatePWD() {
        return validatePWD;
    }

    public void setValidatePWD(String validatePWD) {
        this.validatePWD = validatePWD;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bind_time) {
        this.bindTime = bind_time;
    }
}
