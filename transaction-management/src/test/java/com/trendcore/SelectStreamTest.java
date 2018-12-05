package com.trendcore;


import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Seq;
import com.trendcore.sql.Table;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectStreamTest {

    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = HikariDataSource.get().getDataSource();
    }

    @Test
    public void basicSelect() throws Exception {
        SelectStream select = new SelectStream(dataSource);

        Stream<ResultSetCursor> stream = select.stream("select * from address a where a.address = ?", 1);

        stream.forEach(resultSetCursor -> {
            try {
                ResultSet resultSet = resultSetCursor.getResultSet();
                ResultSetMetaData metaData = resultSet.getMetaData();

                for (int i = 0, cnt = 1; i < metaData.getColumnCount(); i++, cnt++) {
                    System.out.println(resultSet.getObject(cnt));
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


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

        Assert.assertEquals(count,4);
    }

    @Test(expected = Exception.class)
    public void collectSelectResultInListShouldResultInError() throws SQLException {
        SelectStream select = new SelectStream(dataSource);

        Stream<ResultSetCursor> stream = select.stream("select * from address a where a.address = ?", 1);

        List<ResultSetCursor> collect = stream.collect(Collectors.toList());

        for(ResultSetCursor r : collect){
            System.out.println(r.getResultSet().getObject(1));
        }

    }
}
