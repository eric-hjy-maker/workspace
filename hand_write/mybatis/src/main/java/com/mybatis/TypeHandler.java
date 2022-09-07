package com.mybatis;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface TypeHandler<T> {
    void setParameter(PreparedStatement ps, int i, T value) throws SQLException;
}
