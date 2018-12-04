package com.trendcore;

import com.trendcore.sql.Row;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ResultSetIterator implements Iterator<Row> {

    private ResultSet rs;
    private PreparedStatement ps;
    private Connection connection;
    private String sql;

    public ResultSetIterator(Connection connection, String sql) {
        assert connection != null;
        assert sql != null;
        this.connection = connection;
        this.sql = sql;
    }

    public void init() {
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            close();
            //throw new DataAccessException(e);
            throw new RuntimeException();
        }
    }

    @Override
    public boolean hasNext() {
        if (ps == null) {
            init();
        }
        try {
            boolean hasMore = rs.next();
            if (!hasMore) {
                close();
            }
            return hasMore;
        } catch (SQLException e) {
            close();
            throw new RuntimeException();
            //throw new DataAccessException(e);
        }

    }

    void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

            }
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            //nothing we can do here
        }
    }

    @Override
    public Row next() {
        try {
            Row row = new Row() {
                @Override
                public <T> T get(int index) {
                    return null;
                }
            };
            return row;
        } catch (Exception e) {
            close();
            throw e;
        }
    }

}
