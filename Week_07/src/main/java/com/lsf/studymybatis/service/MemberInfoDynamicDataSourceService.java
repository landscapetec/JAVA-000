package com.lsf.studymybatis.service;


import com.lsf.studymybatis.config.DynamicSwitchDataSource;
import com.lsf.studymybatis.entity.MemberInfo;
import com.lsf.studymybatis.mapper.MemberInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInfoDynamicDataSourceService {

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @DynamicSwitchDataSource(dataSource = "dsmaster")
    public List<MemberInfo> findAll(MemberInfo memberInfo) {
        return memberInfoMapper.findAll(memberInfo);
    }

    @DynamicSwitchDataSource(dataSource = "dsslave0")
    public List<MemberInfo> findAllSlave(MemberInfo memberInfo) {
        return memberInfoMapper.findAll(memberInfo);
    }
}
