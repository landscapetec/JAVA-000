package com.lsf.study.shardingsphere.services;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class XANamespaceOrderService {

    @Autowired
    private DataSource dataSource;

    public void insertOrder(final int count) throws SQLException {
        TransactionTypeHolder.set(TransactionType.XA);
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_order (id, status) VALUES (?, ?)");
            for (int i = 0; i < count; i++) {
                preparedStatement.setObject(1, i);
                preparedStatement.setObject(2, "init");
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (Exception ex) {
            connection.rollback();
        }
        finally {
            TransactionTypeHolder.clear();
        }
        printData();
    }

    public void insertOrderFail(final int count) throws SQLException {
        TransactionTypeHolder.set(TransactionType.XA);
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_order (id, status) VALUES (?, ?)");
            for (int i = 0; i < count; i++) {
                preparedStatement.setObject(1, i);
                preparedStatement.setObject(2, "init");
                preparedStatement.executeUpdate();
            }
            throw new Exception("asdfasdfasdfasdfasdfasdf");
        } catch (Exception ex) {
            connection.rollback();
        }
        finally {
            TransactionTypeHolder.clear();
        }


        printData();
    }


    public void printData() throws SQLException {
        System.out.println("Print Order Data");
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT id, status FROM t_order");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String orderId = resultSet.getString("id");
                String status = resultSet.getString("status");
                System.out.printf("orderId = %s, status = %s \n", orderId, status);
            }
        }
    }
}
