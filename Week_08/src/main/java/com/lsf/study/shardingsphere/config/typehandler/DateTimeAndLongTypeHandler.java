package com.lsf.study.shardingsphere.config.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@MappedJdbcTypes(JdbcType.BIGINT)
@MappedTypes(Date.class)
public class DateTimeAndLongTypeHandler extends BaseTypeHandler<Date> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter.getTime());
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long timespan = rs.getLong(columnName);
        return new Date(timespan);
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long timespan = rs.getLong(columnIndex);
        return new Date(timespan);
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Long dbValue = cs.getLong(columnIndex);
        return new Date(dbValue);
    }
}
