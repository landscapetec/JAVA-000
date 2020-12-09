package com.lsf.study.shardingsphere.services;

import com.lsf.study.shardingsphere.StudyShardingSphereApplication;
import com.lsf.study.shardingsphere.entity.MemberInfo;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@SpringBootTest(classes = {StudyShardingSphereApplication.class})
@RunWith(SpringRunner.class)
public class MemberInfoServiceTest {

    @Autowired
    private MemberInfoService memberInfoService;

    private Random random=new Random(100);

    @Test
    public void testInsertMemberInfo() {
        for (int i = 1; i < 321; i++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setId(1000 + i);
            memberInfo.setUserNickname("asdfasdf");
            memberInfo.setUserCode("adf");
            memberInfo.setPhone("12441231231");
            memberInfo.setActivity(true);
            memberInfo.setDeleted(false);
            memberInfo.setLastLoginTime(new Date(System.currentTimeMillis()));
            memberInfo.setAddTime(new Date(System.currentTimeMillis()+random.nextInt(100)));

            long id = memberInfoService.insert(memberInfo);
            System.out.println("插入数据:" + id + "    " + memberInfo.getId());
        }
    }

    @Test
    public void testUpdateMemberInfo() {
        for (int i = 1; i < 321; i++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setId(1000 + i);
            memberInfo.setUserNickname("eeeeeeeeeeee");
            memberInfo.setUserCode("adf");
            memberInfo.setPhone("12441231231");
            memberInfo.setActivity(true);
            memberInfo.setDeleted(false);
            memberInfo.setLastLoginTime(new Date(System.currentTimeMillis()));
            memberInfo.setAddTime(new Date(System.currentTimeMillis()+random.nextInt(100)));

            memberInfoService.update(memberInfo);
            System.out.println("修改数据:" + i + "    " + memberInfo.getId());
        }
    }

    @Test
    public void testDeleteMemberInfo() {
        for (int i = 1; i < 10; i++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setId(1000 + i);
            memberInfo.setUserNickname("eeeeeeeeeeee");
            memberInfo.setUserCode("adf");
            memberInfo.setPhone("12441231231");
            memberInfo.setActivity(true);
            memberInfo.setDeleted(false);
            memberInfo.setLastLoginTime(new Date(System.currentTimeMillis()));
            memberInfo.setAddTime(new Date(System.currentTimeMillis()+random.nextInt(100)));

            memberInfoService.delete(memberInfo);
            System.out.println("插入数据:" + i + "    " + memberInfo.getId());
        }
    }

} 
