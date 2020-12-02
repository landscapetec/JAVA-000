package com.lsf.studymybatis.service;


import com.lsf.studymybatis.entity.MemberInfo;
import com.lsf.studymybatis.mapper.MemberInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInfoShardingService {

    @Autowired
    private MemberInfoMapper memberInfoMapper;


    public List<MemberInfo> findAll(MemberInfo memberInfo) {
        return memberInfoMapper.findAll(memberInfo);
    }
}
