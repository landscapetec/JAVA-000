package com.lsf.study.shardingsphere.mapper;


import com.lsf.study.shardingsphere.entity.MemberInfo;

import java.util.List;

public interface MemberInfoMapper {

    long insert(MemberInfo memberInfo);

    void update(MemberInfo memberInfo);

    void delete(long id);

    List<MemberInfo> findAll(MemberInfo memberInfo);
}
