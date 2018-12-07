package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Tuple<T> implements Row {

    Object tuple[];

    public Tuple(int columnCount) {
        tuple = new Object[columnCount];
    }

    public Tuple(ResultSetMetaData metaData) {
        try {
            tuple = new Object[metaData.getColumnCount()];
        } catch (SQLException e) {
            //TODO exception handling
            throw new RuntimeException();
        }
    }

    @Override
    public void set(Column column, Object o) {
        tuple[column.getIndex()] = o;
    }

    @Override
    public Object get(Column id) {
        return tuple[id.getIndex()];
    }

    @Override
    public void populate(ResultSet resultSet) {
        try {
            for (int i = 0, cnt = 1; i < tuple.length; i++, cnt++) {
                tuple[i] = resultSet.getObject(cnt);
            }
        } catch (SQLException e) {
            //TODO Exception handling
            throw new RuntimeException();
        }
    }
}
