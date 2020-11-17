package com.lsf.hw_1006;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnectionUtil {
    private static DataSource dataSource;
    private static Object lockObject = new Object();

    static {
        synchronized (lockObject) {
            if (dataSource == null) {
                HikariConfig config = new HikariConfig("/HikariConfig.properties");
                dataSource = new HikariDataSource(config);
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

}
