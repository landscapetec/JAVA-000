package com.lsf.studymybatis.mapper;


import com.lsf.studymybatis.entity.MemberInfo;

import java.util.List;

public interface MemberInfoMapper {
    long insert(MemberInfo memberInfo);

    List<MemberInfo> findAll(MemberInfo memberInfo);
}
