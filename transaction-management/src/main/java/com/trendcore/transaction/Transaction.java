package com.trendcore.transaction;

import javax.sql.DataSource;

public class Transaction {

    private DataSource dataSource;

    public Transaction(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}
