package com.lsf.hw_1006;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class JDBCUtilHikariConnectionTest {

    private JDBCUtil jdbcHelper;

    @Before
    public void Before() throws SQLException {
        jdbcHelper = new JDBCUtil(HikariConnectionUtil.getConnection());
    }

    @Test
    public void testQuery() throws IllegalAccessException, SQLException, InstantiationException {
        List<UserInfo> result = jdbcHelper.query("select * from userinfo", UserInfo.class);
        System.out.println(JSONObject.toJSON(result));
    }

    @Test
    public void testInsert() throws SQLException {
        int result = jdbcHelper.executeSql("insert into userinfo(code,`name`) values('001','1213')");
        System.out.println(JSONObject.toJSON(result));
    }

    @Test
    public void testInsertReturnId() throws SQLException {
        int id = jdbcHelper.insert("insert into userinfo(code,`name`) values('001','1213')");
        System.out.println("执行Sql，返回主键：" + id);
    }

    @Test
    public void testUpdate() throws SQLException {
        int result = jdbcHelper.executeSql("update userinfo set code='001',`name`='lehman' where id=05");
        System.out.println(JSONObject.toJSON(result));
    }
}