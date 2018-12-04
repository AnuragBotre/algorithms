package com.trendcore;

import com.zaxxer.hikari.HikariConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HikariDataSource {

    private static HikariConfig config = new HikariConfig();

    private static volatile HikariDataSource hikariDataSource;

    private static Lock doubleCheckLock = new ReentrantLock();

    private  com.zaxxer.hikari.HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost/sakila");
        config.setUsername("anurag");
        config.setPassword("anurag");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    }

    private HikariDataSource() {
        ds = new com.zaxxer.hikari.HikariDataSource(config);
    }

    public static HikariDataSource get() {
        if (hikariDataSource == null) {
            try {
                doubleCheckLock.lock();
                if (hikariDataSource == null) {

                    hikariDataSource = new HikariDataSource();
                }
            } finally {
                doubleCheckLock.unlock();
            }
        }

        return hikariDataSource;
    }

    public com.zaxxer.hikari.HikariDataSource getDataSource() {
        return ds;
    }
}
