package com.trendcore;

import com.trendcore.sql.Row;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Deprecated
public class SQL {

    public static Stream<Row> select(Connection connection, String sql,Object... params) {
        /*Stream<Row> stream = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(
                        new ResultSetIterator(connection, () -> {
                            PreparedStatement preparedStatement = null;
                            try {
                                preparedStatement = connection.prepareStatement(sql);
                                int cnt = 1;
                                for(Object param : params){
                                    preparedStatement.setObject(cnt,param);
                                    cnt++;
                                }
                            } catch (SQLException e) {
                                //TODO Exception handling
                            }

                            return preparedStatement;
                        }), 0), false);*/
        Stream<Row> stream = null;
        return stream;
    }

}
