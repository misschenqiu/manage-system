package com.starda.managesystem.common;

import com.starda.managesystem.util.AES;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.common
 * @ClassName: AESEncryptHandler
 * @Author: chenqiu
 * @Description: 继承BaseTypeHandler ，实现对数据的转换
 * @Date: 2021/7/29 15:36
 * @Version: 1.0
 */
public class AESEncryptHandler extends BaseTypeHandler {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, AES.encrypt((String)parameter));
    }
    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return AES.decrypt(columnValue);
    }
    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return AES.decrypt(columnValue);
    }
    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return AES.decrypt(columnValue);
    }
}
