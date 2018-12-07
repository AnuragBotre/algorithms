package com.trendcore;

import com.trendcore.sql.Row;
import com.trendcore.sql.Table;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SelectStream {

    private DataSource dataSource;

    public SelectStream(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public <T> Stream<Row<T>> stream(BiFunction<ResultSetMetaData, ResultSet, Row<T>> mapper, String sql, Object... params) {
        return getStream(sql,mapper,params);
    }

    public Stream<Row> stream(String sql, Object... params) {
        BiFunction<ResultSetMetaData, ResultSet, Row> mapper = Table::row;
        return getStream(sql, mapper, params);
    }

    private <T> Stream<T> getStream(String sql, BiFunction<ResultSetMetaData, ResultSet, T> mapper, Object[] params) {

        Consumer<SingleResultSetWrapper> singleResultSetWrapperConsumer = SingleResultSetWrapper::close;

        ProxyConnectionForStream proxyConnectionForStream = new ProxyConnectionForStream();
        @SuppressWarnings(value = {"unchecked"})
        ResultSetIterator<T> resultSetIterator = new ResultSetIterator<T>()
                .resultSetSupplier(() -> {
                    try {
                        Connection connection = dataSource.getConnection();
                        proxyConnectionForStream.setConnection(connection);
                        SingleResultSetWrapper resultSet = proxyConnectionForStream.getResultSet(sql, params);
                        return resultSet;
                    } catch (SQLException e) {
                        throw new RuntimeException();
                    }

                })
                .resultSetMapper(mapper)
                .onClose(singleResultSetWrapperConsumer);

        Stream<T> stream = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(resultSetIterator, 0), false);

        stream.onClose(proxyConnectionForStream::close);

        return stream;
    }
}
