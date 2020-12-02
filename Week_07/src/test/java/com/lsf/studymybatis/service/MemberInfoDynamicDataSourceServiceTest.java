package com.lsf.studymybatis.service;

import com.lsf.studymybatis.MybatisApplication;
import com.lsf.studymybatis.entity.MemberInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public class MemberInfoDynamicDataSourceServiceTest {

    @Autowired
    private MemberInfoDynamicDataSourceService memberInfoDynamicDataSourceService;

    @Test
    public void findAll() {
        MemberInfo memberInfo= new MemberInfo();
        memberInfo.setUserCode("adf_993");
        List<MemberInfo> result= memberInfoDynamicDataSourceService.findAll(memberInfo);
        System.out.println("总行数"+result.size());
    }

    @Test
    public void findAllSlave() {
        MemberInfo memberInfo= new MemberInfo();
        memberInfo.setUserCode("adf_993");
        List<MemberInfo> result= memberInfoDynamicDataSourceService.findAllSlave(memberInfo);
        System.out.println("总行数"+result.size());
    }

}