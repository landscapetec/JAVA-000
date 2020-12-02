package com.lsf.studymybatis;

import com.lsf.studymybatis.entity.MemberInfo;
import com.lsf.studymybatis.mapper.MemberInfoMapper;
import com.lsf.studymybatis.service.MemberInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public class MemberInfoServiceTest {

    @Autowired
    private MemberInfoService memberInfoService;

    private List<MemberInfo> listData;


    @Before
    public void before(){
        listData = new ArrayList<>();
        MemberInfo memberInfo = null;
        for (int i = 0; i < 10000; i++) {
            memberInfo = new MemberInfo();
            memberInfo.setUserNickname("Lehman_00"+i);
            memberInfo.setUserCode("adf_"+i);
            memberInfo.setPhone("12441231231");
            memberInfo.setActivity(true);
            memberInfo.setDeleted(false);
            memberInfo.setLastLoginTime(new Date(System.currentTimeMillis()));
            memberInfo.setAddTime(new Date(System.currentTimeMillis()));
            listData.add(memberInfo);
        }
    }

    @Test
    public void testInsertMemberInfo() {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setUserNickname("asdfasdf");
        memberInfo.setUserCode("adf");
        memberInfo.setPhone("12441231231");
        memberInfo.setActivity(true);
        memberInfo.setDeleted(false);
        memberInfo.setLastLoginTime(new Date(System.currentTimeMillis()));
        memberInfo.setAddTime(new Date(System.currentTimeMillis()));

        long id = memberInfoService.insert(memberInfo);
        System.out.println("插入数据:" + id + "    " + memberInfo.getId());
    }

    @Test
    public void testBatchInsert() {
        memberInfoService.batchInsert(listData);
    }

    @Test
    public void testBatchInsertByJDBC() throws SQLException {
        memberInfoService.batchInsertByJDBC(listData);
    }


}
