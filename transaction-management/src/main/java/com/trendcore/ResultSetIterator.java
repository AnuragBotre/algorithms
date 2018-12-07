package com.trendcore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ResultSetIterator<T> implements Iterator {

    private BiFunction<ResultSetMetaData, ResultSet, T> mapper;
    private SingleResultSetWrapper rs;
    private PreparedStatement ps;
    private Supplier<SingleResultSetWrapper> resultSetSupplier;
    private Consumer<SingleResultSetWrapper> onClose;
    private ResultSetMetaData metaData;

    public ResultSetIterator() {

    }

    public ResultSetIterator resultSetSupplier(Supplier<SingleResultSetWrapper> supplier) {
        this.resultSetSupplier = supplier;
        return this;
    }

    public ResultSetIterator resultSetMapper(BiFunction<ResultSetMetaData, ResultSet, T> mapper) {
        this.mapper = mapper;
        return this;
    }

    public ResultSetIterator onClose(Consumer<SingleResultSetWrapper> onClose) {
        this.onClose = onClose;
        return this;
    }

    public void init() {
        rs = resultSetSupplier.get();
    }

    @Override
    public boolean hasNext() {

        try {

            if (rs == null) {
                init();
                metaData = rs.getResultSet().getMetaData();
            }

            boolean hasMore = rs.getResultSet().next();
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
        onClose.accept(rs);
    }

    @Override
    public T next() {
        try {
            return mapper.apply(metaData, rs.getResultSet());
        } catch (Exception e) {
            close();
            throw e;
        }
    }

}
