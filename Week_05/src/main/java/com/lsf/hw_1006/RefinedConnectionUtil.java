package com.lsf.hw_1006;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RefinedConnectionUtil {
    private static volatile Connection connection = null;

    static {
        String driveName = "com.mysql.cj.jdbc.Driver";
        String connectString = "jdbc:mysql://localhost:3306/order_db";
        String userName = "root";
        String pwd = "tyl2011_1990";
        try {
            Class.forName(driveName);
            connection = (Connection) DriverManager.getConnection(connectString, userName, pwd);
            connection.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }


}
