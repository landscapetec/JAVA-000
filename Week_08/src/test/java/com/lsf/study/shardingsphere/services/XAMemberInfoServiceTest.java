package com.lsf.study.shardingsphere.services;

import com.lsf.study.shardingsphere.StudyShardingSphereApplication;
import com.lsf.study.shardingsphere.entity.MemberInfo;
import org.apache.shardingsphere.transaction.xa.atomikos.manager.AtomikosTransactionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest(classes = {StudyShardingSphereApplication.class})
@RunWith(SpringRunner.class)
public class XAMemberInfoServiceTest {

    @Autowired
    private XAMemberInfoService memberInfoService;

    private Random random = new Random(100);

    private List<MemberInfo> listData;


    @Before
    public void before() {
        listData = new ArrayList<>();
        MemberInfo memberInfo = null;
        for (int i = 0; i < 100; i++) {
            memberInfo = new MemberInfo();
            memberInfo.setId(System.currentTimeMillis() + i);
            memberInfo.setUserNickname("Lehman_00" + i);
            memberInfo.setUserCode("adf_" + i);
            memberInfo.setPhone("12441231231");
            memberInfo.setActivity(true);
            memberInfo.setDeleted(false);
            memberInfo.setLastLoginTime(new Date(System.currentTimeMillis()));
            memberInfo.setAddTime(new Date(System.currentTimeMillis()));
            listData.add(memberInfo);
        }
    }

    @Test
    public void testInsert() throws Exception {
        memberInfoService.insert(listData);
    }

    @Test
    public void testInsertFail() throws Exception {
        memberInfoService.insertFail(listData);
    }

    @Test
    public void testInsertOrder() throws Exception {
        memberInfoService.insertOrder(600);
    }

    @Test
    public void testInsertOrderFail() throws Exception {
        memberInfoService.insertOrderFail(600);
    }


} 
