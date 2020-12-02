package com.lsf.studymybatis.mapper;


import com.lsf.studymybatis.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserInfoMapper {

    @Select("select * from userinfo")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "id", column = "id")
    })
    public List<UserInfo> findAll();

    @Select("select * from userinfo where id=#{id}")
    UserInfo getById(Long id);

    @Insert({"insert into userinfo(code,name) values(#{code},#{name})"})
    void insert(UserInfo user);

    @Update({"update userinfo set code=${code},name=#{name} where id=#{id}"})
    void update(UserInfo user);

    @Delete("delete from userinfo where id=#{id}")
    void delete(Long id);
}
