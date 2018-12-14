package com.trendcore;

import com.zaxxer.hikari.HikariConfig;

import javax.sql.DataSource;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HikariDataSource {

    public static HikariConfig config = new HikariConfig();

    private static volatile HikariDataSource hikariDataSource;

    private static Lock doubleCheckLock = new ReentrantLock();

    private  com.zaxxer.hikari.HikariDataSource ds;

    public static String SCHEMA = "sakila";


    private HikariDataSource(String schema) {

        config.setJdbcUrl("jdbc:mysql://localhost/" + schema);
        config.setUsername("anurag");
        config.setPassword("anurag");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new com.zaxxer.hikari.HikariDataSource(config);
    }

    public static HikariDataSource get() {
        if (hikariDataSource == null) {
            try {
                doubleCheckLock.lock();
                if (hikariDataSource == null) {

                    hikariDataSource = new HikariDataSource(SCHEMA);
                }
            } finally {
                doubleCheckLock.unlock();
            }
        }

        return hikariDataSource;
    }

    public static HikariDataSource get(String schema) {
        if (hikariDataSource == null) {
            try {
                doubleCheckLock.lock();
                if (hikariDataSource == null) {

                    hikariDataSource = new HikariDataSource(schema);
                }
            } finally {
                doubleCheckLock.unlock();
            }
        }

        return hikariDataSource;
    }

    public DataSource getDataSource() {
        return ds;
    }
}
