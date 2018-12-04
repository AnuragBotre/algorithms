package com.trendcore;

import com.trendcore.sql.Row;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.function.Supplier;

public class ResultSetIterator implements Iterator<Row> {

    private ResultSet rs;
    private PreparedStatement ps;
    private Connection connection;
    private Supplier<PreparedStatement> preparedStatementSupplier;

    public ResultSetIterator(Connection connection, Supplier<PreparedStatement> supplier) {
        assert connection != null;
        this.connection = connection;
        this.preparedStatementSupplier = supplier;
    }

    public void init() {
        try {
            ps = preparedStatementSupplier.get();
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
                //TODO - log exception
            }
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            //nothing we can do here
            //TODO - log exception
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
