package com.trendcore;


import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Seq;
import com.trendcore.sql.Table;
import org.junit.Test;

import javax.sql.DataSource;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
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

    @Test
    public void tableAsStream() throws Exception {
        class Result{
            public Column<Integer> ID;
            public Column<String> NAME;
            public Column<Date> BIRTHDATE;
            public Column<Integer> USER_DETAILS;

            public String a;
            public String b;
        }

        Result r = new Result();

        Class currentClass = r.getClass();
        Seq seq = new Seq();
        long count = Arrays.asList(currentClass.getDeclaredFields()).stream().filter(field -> field.getType().isAssignableFrom(Column.class))
                .count();

        System.out.println(count);

        //System.out.println(r.BIRTHDATE.getType());
    }
}
