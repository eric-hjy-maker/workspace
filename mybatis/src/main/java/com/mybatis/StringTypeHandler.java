package com.mybatis;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author hjy
 **/
public class StringTypeHandler implements TypeHandler<String> {

    @Override
    public void setParameter(PreparedStatement ps, int i, String value) throws SQLException {
        ps.setString(i, value);
    }
}
