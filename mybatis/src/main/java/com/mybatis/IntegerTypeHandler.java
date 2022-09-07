package com.mybatis;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author hjy
 **/
public class IntegerTypeHandler implements TypeHandler<Integer> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Integer value) throws SQLException {
        ps.setInt(i, value);
    }
}
