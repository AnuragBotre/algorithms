package com.trendcore;


import com.trendcore.sql.Row;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.stream.Stream;

public class SelectStreamTest {

    @Test
    public void basicSelect() throws Exception {
        DataSource dataSource = HikariDataSource.get().getDataSource();

        SelectStream select = new SelectStream(dataSource);

        select.stream(ResultSet.class,"select * from address a where a.address = ?",1);

        /*Stream<Row> select = SQL.select(dataSource.getConnection(), );
        select.forEach(row -> {
            System.out.println(row);
        });*/
    }
}
