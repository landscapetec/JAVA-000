package com.lsf.studymybatis.service;

import com.lsf.studymybatis.entity.MemberInfo;
import com.lsf.studymybatis.mapper.MemberInfoMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlMasterSlaveDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MemberInfoService {
    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public long insert(MemberInfo memberInfo) {
        return memberInfoMapper.insert(memberInfo);
    }

    public void batchInsert(List<MemberInfo> listData) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        MemberInfoMapper sqlStatement = sqlSession.getMapper(MemberInfoMapper.class);
        for (MemberInfo listDatum : listData) {
            sqlStatement.insert(listDatum);
        }
        sqlSession.commit();
        sqlSession.close();
    }

    public void batchInsertByJDBC(List<MemberInfo> listData) throws SQLException {
        String sql = "insert into t_member_info(user_code,user_nick_name,phone,deleted,activity,add_time,last_login_time)\n" +
                " values(?,?,?,?,?,?,?)";
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement prest = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        for (MemberInfo memberInfo : listData) {
            prest.setString(1, memberInfo.getUserCode());
            prest.setString(2, memberInfo.getUserNickname());
            prest.setString(3, memberInfo.getPhone());
            prest.setBoolean(4, memberInfo.isDeleted());
            prest.setBoolean(5, memberInfo.isActivity());
            prest.setLong(6, memberInfo.getAddTime().getTime());
            prest.setLong(7, memberInfo.getLastLoginTime().getTime());
            prest.addBatch();
        }

        prest.executeBatch();
        connection.commit();
        connection.close();
    }

}
