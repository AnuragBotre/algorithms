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


    public <T extends Table> Stream<Row<T>> stream(Class<T> t, String sql, Object... params) {

        ProxyConnectionForStream proxyConnectionForStream = new ProxyConnectionForStream();

        @SuppressWarnings(value = {"unchecked"})
        ResultSetIterator<Row<T>> resultSetIterator = new ResultSetIterator<>()
                                        .resultSet(() -> {
                                            try {
                                                Connection connection = dataSource.getConnection();
                                                proxyConnectionForStream.setConnection(connection);
                                                SingleResultSetWrapper resultSet = proxyConnectionForStream.getResultSet(sql, params);
                                                return resultSet;
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            return null;
                                        })
                                        .resultSetMapper((o, o2) -> null)
                                        .onClose(singleResultSetWrapper -> {
                                            proxyConnectionForStream.close();
                                        });

        Stream<Row<T>> stream = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(resultSetIterator, 0), false);

        stream.onClose(proxyConnectionForStream::close);

        return stream;
    }

    public Stream<Row> stream(String sql, Object... params) {
        ProxyConnectionForStream proxyConnectionForStream = new ProxyConnectionForStream();

        BiFunction<ResultSetMetaData, ResultSet, Row> mapper = Table::row;

        Consumer<SingleResultSetWrapper> singleResultSetWrapperConsumer = SingleResultSetWrapper::close;

        @SuppressWarnings(value = {"unchecked"})
        ResultSetIterator<Row> resultSetIterator = new ResultSetIterator<Row>()
                .resultSet(() -> {
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

        Stream<Row> stream = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(resultSetIterator, 0), false);

        stream.onClose(proxyConnectionForStream::close);

        return stream;
    }
}
