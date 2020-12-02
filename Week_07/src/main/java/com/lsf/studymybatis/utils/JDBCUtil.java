package com.lsf.studymybatis.utils;

import com.lsf.studymybatis.entity.MemberInfo;
import com.lsf.studymybatis.mapper.MemberInfoMapper;
import com.mysql.cj.xdevapi.SqlStatement;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

public class JDBCUtil {
    private Connection connection;

    public int executeSql(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        int result = 0;
        try {
            result = statement.executeUpdate(sql);
            connection.commit();
        } catch (Exception exception) {
            connection.rollback();
        }
        return result;
    }
}
