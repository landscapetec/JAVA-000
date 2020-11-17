package com.lsf.hw_1006;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
    private Connection connection;

    public JDBCUtil(Connection connection) {
        this.connection = connection;
    }

    public <T extends Object> List<T> query(String queryString, Class<T> clz) throws SQLException, IllegalAccessException, InstantiationException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryString);
        Field[] fields = clz.getDeclaredFields();
        List<T> result = new ArrayList<>();
        T resultObject = null;
        while (resultSet.next()) {
            resultObject = clz.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType() == Integer.class) {
                    field.set(resultObject, Integer.valueOf(resultSet.getInt(field.getName())));
                } else if (field.getType() == int.class) {
                    field.set(resultObject, resultSet.getInt(field.getName()));
                } else if (field.getType() == String.class) {
                    field.set(resultObject, resultSet.getString(field.getName()));
                }
            }
            result.add(resultObject);
        }
        return result;
    }

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

    public int insert(String sql) throws SQLException {
        int result = -1;
        try {
            PreparedStatement prep = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            prep.executeUpdate();
            ResultSet resultSet = prep.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            connection.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = -1;
            connection.rollback();
        }

        return result;
    }
}
