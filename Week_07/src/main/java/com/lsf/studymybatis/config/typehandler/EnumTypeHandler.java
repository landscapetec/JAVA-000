package com.lsf.studymybatis.config.typehandler;

import com.lsf.studymybatis.entity.BaseEnum;
import com.lsf.studymybatis.entity.UserInfoValidateType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(value = {UserInfoValidateType.class})
public class EnumTypeHandler<T> extends BaseTypeHandler<BaseEnum<T>> {
    private final Class<BaseEnum> type;

    public EnumTypeHandler(Class<BaseEnum> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("set value");
        if (jdbcType == null) {
            ps.setString(i, parameter.getCode());
        } else {
            ps.setObject(i, parameter.getCode(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public BaseEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String enumCode = rs.getString(columnName);
        return enumCode == null ? null : BaseEnum.valueOfEnum(type, enumCode);
    }

    @Override
    public BaseEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String enumCode = rs.getString(columnIndex);
        return enumCode == null ? null : BaseEnum.valueOfEnum(type, enumCode);
    }

    @Override
    public BaseEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String enumCode = cs.getString(columnIndex);
        return enumCode == null ? null : BaseEnum.valueOfEnum(type, enumCode);
    }
}
