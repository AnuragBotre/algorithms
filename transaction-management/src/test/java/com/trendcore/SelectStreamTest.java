package com.trendcore;


import com.trendcore.sql.Row;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.stream.Stream;

public class SelectStreamTest {

    @Test
    public void basicSelect() throws Exception {
        DataSource dataSource = HikariDataSource.get().getDataSource();

        SelectStream select = new SelectStream(dataSource);

        Stream<ResultSetCursor> stream = select.stream("select * from address a where a.address = ?", 1);

        stream.forEach(resultSetCursor -> {
            try {
                ResultSetMetaData metaData = resultSetCursor.getResultSet().getMetaData();

                for (int i = 0, cnt = 1; i < metaData.getColumnCount(); i++, cnt++) {
                    System.out.println(resultSetCursor.getResultSet().getObject(cnt));
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        /*Stream<Row> select = SQL.select(dataSource.getConnection(), );
        select.forEach(row -> {
            System.out.println(row);
        });*/
    }
}
