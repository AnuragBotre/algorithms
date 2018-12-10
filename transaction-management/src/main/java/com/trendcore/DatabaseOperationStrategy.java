package com.trendcore;

import javax.sql.DataSource;

public class DatabaseOperationStrategy {


    private DataSource dataSource;

    public void dataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
