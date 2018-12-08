package com.trendcore;


import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Seq;
import com.trendcore.sql.Table;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
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

        Stream<Row> stream = select.stream("select * from address a where a.address = ?", 1);

        stream.forEach(actorRow -> {
            Column id = new Column();
            id.setIndex(1);
            System.out.println(actorRow.get(id));
        });

    }

    @Test
    public void tableAsStream() throws Exception {
        class Result {
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

        Assert.assertEquals(count, 4);
    }

    public static class Result {
        public static Column<Integer> ADDRESS_ID;
    }

    @Test
    public void collectSelectResultInListShouldResultInError() throws SQLException {
        SelectStream select = new SelectStream(dataSource);


        Stream<Row<Result>> stream = select.stream((metaData, resultSet) -> {
            Row<Result> r = new Tuple<Result>(metaData);
            r.populate(resultSet);
            return r;
        }, "select " + columns(Result.class) + " from address a where a.address_id = ?", 2);

        List<Row> collect = stream.collect(Collectors.toList());

        Column<Integer> c = new Column<>(Integer.class, 1);

        for (Row<Result> r : collect) {
            Integer integer = r.get(Result.ADDRESS_ID);
            System.out.println(integer);
        }

    }

    private <T> String columns(Class<T> aClass) {
        TableDescriptor tableDescriptor = Table.init(aClass);
        return tableDescriptor.getColums().stream().map(column -> column.name()).collect(Collectors.joining(","));
    }

    @Test
    public void syntacticSugarForTableAlias() {
        Actor a = new Actor();
        Actor b = Table.as(Actor.class);
        System.out.println(b.ID);
    }
}
