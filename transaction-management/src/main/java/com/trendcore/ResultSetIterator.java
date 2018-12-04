package com.trendcore;

import com.trendcore.sql.Row;
import com.trendcore.sql.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.function.Supplier;

public class ResultSetIterator implements Iterator {

    private ResultSet rs;
    private PreparedStatement ps;
    private Connection connection;
    private Supplier<PreparedStatement> preparedStatementSupplier;
    private ResultSetCursor resultSetCursor;

    public ResultSetIterator(Connection connection, Supplier<PreparedStatement> supplier) {
        assert connection != null;
        this.connection = connection;
        this.preparedStatementSupplier = supplier;
    }

    public void init() {
        try {
            ps = preparedStatementSupplier.get();
            rs = ps.executeQuery();

            resultSetCursor = new ResultSetCursor(rs);

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
    public ResultSetCursor next() {
        try {
            int i = 0;

            return resultSetCursor;
        } catch (Exception e) {
            close();
            throw e;
        }
    }

}
