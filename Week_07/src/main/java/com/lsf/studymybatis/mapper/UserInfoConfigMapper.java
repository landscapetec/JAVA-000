package com.lsf.studymybatis.mapper;


import com.lsf.studymybatis.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserInfoConfigMapper {
    List<UserInfo> findAll();

    UserInfo getById(Long id);

    List<UserInfo> query(UserInfo userInfo);

    long insert(UserInfo user);

    void update(UserInfo user);

    void delete(Long id);
}
