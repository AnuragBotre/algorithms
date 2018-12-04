package com.trendcore;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SelectStream {

    private DataSource dataSource;

    public SelectStream(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Stream<ResultSetCursor> stream(String sql, Object... params) {

        try {
            Connection connection = dataSource.getConnection();

            Stream<ResultSetCursor> stream = StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(
                            new ResultSetIterator(connection, () -> {
                                PreparedStatement preparedStatement = null;
                                try {
                                    preparedStatement = connection.prepareStatement(sql);
                                    int cnt = 1;
                                    for (Object param : params) {
                                        preparedStatement.setObject(cnt, param);
                                        cnt++;
                                    }
                                } catch (SQLException e) {
                                    //TODO Exception handling
                                }

                                return preparedStatement;
                            }), 0), false);

            stream.onClose(() -> {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    //TODO exception handling
                }
            });

            return stream;

        } catch (SQLException e) {
            //e.printStackTrace();
            //TODO : Exception handling
            throw new RuntimeException();
        }

    }
}
