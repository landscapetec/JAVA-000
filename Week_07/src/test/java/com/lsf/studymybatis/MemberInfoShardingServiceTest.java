package com.lsf.studymybatis;

import com.lsf.studymybatis.entity.MemberInfo;
import com.lsf.studymybatis.service.MemberInfoService;
import com.lsf.studymybatis.service.MemberInfoShardingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public class MemberInfoShardingServiceTest {
    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private MemberInfoShardingService memberInfoShardingService;

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
    public void testMasterSlave() throws IOException, SQLException {
        MemberInfo memberInfo= new MemberInfo();
        memberInfo.setUserCode("adf_993");
        List<MemberInfo> result= memberInfoShardingService.findAll(memberInfo);
        System.out.println("总行数"+result.size());
    }
}
