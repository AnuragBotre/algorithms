package com.trendcore;


import org.junit.Test;

import javax.sql.DataSource;

public class SelectStream {

    @Test
    public void basicSelect() throws Exception {
        DataSource dataSource = HikariDataSource.get().getDataSource();
        SQL.select(dataSource.getConnection(),"select * from address a where a.address = ?",(resultSet, l) -> {

            System.out.println(resultSet);
        },1);
    }
}
