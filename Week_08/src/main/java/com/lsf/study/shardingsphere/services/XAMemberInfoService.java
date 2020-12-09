package com.lsf.study.shardingsphere.services;

import com.lsf.study.shardingsphere.entity.MemberInfo;
import com.lsf.study.shardingsphere.mapper.MemberInfoMapper;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class XAMemberInfoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;


    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void insert(List<MemberInfo> memberInfos) throws Exception {
        for (MemberInfo memberInfo : memberInfos) {
            memberInfoMapper.insert(memberInfo);
        }
    }

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void insertFail(List<MemberInfo> memberInfos) throws Exception {
        for (MemberInfo memberInfo : memberInfos) {
            memberInfoMapper.insert(memberInfo);
        }
        throw new Exception("模拟失败场景");
    }


    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public TransactionType insertOrder(final int count) {

        return jdbcTemplate.execute("INSERT INTO t_order (id, status) VALUES (?, ?)",
                (PreparedStatementCallback<TransactionType>) preparedStatement -> {
            doInsert(count, preparedStatement);
            return TransactionTypeHolder.get();
        });
    }

    /**
     * Execute XA with exception.
     *
     * @param count insert record count
     */
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void insertOrderFail(final int count) {
        jdbcTemplate.execute("INSERT INTO t_order (id, status) VALUES (?, ?)",
                (PreparedStatementCallback<TransactionType>) preparedStatement -> {
                    doInsert(count, preparedStatement);
                    throw new SQLException("mock transaction failed");
                });
    }

    private void doInsert(final int count, final PreparedStatement preparedStatement) throws SQLException {
        for (int i = 0; i < count; i++) {
            preparedStatement.setObject(1, i);
            preparedStatement.setObject(2, "init");
            preparedStatement.executeUpdate();
        }
    }
}
