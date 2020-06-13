package io.github.donggi.reminder.enums;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import io.github.donggi.reminder.util.EnumUtil;
import lombok.NonNull;

@MappedJdbcTypes(JdbcType.INTEGER)
public class EnumValueTypeHandler<T extends Enum<T> & EnumValue> extends BaseTypeHandler<EnumValue> {

    private final @NonNull Map<Integer, T> map;

    public EnumValueTypeHandler(@NonNull Class<T> type) {
        this.map = EnumUtil.asMap(type.getEnumConstants()[0]);
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EnumValue parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, EnumValue parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public EnumValue getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        if (rs.wasNull())
            return null;
        return getEnumByValue(value);
    }

    @Override
    public EnumValue getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        if (rs.wasNull())
            return null;
        return getEnumByValue(value);
    }

    @Override
    public EnumValue getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getNullableResult(cs.getResultSet(), columnIndex);
    }


    private T getEnumByValue(int value) {
        @NonNull T t = map.get(value);
        return t;
    }
}
