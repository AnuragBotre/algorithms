package com.trendcore.transaction;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;

public class TransactionHandler {

    private DataSource dataSource;

    public TransactionHandler(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void execute(Transaction transaction) throws Exception {

        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {

            System.out.println("Starting transaction");
            transaction.execute(connection);
            System.out.println("Committing transaction");
            connection.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("Rolling back...");
            connection.rollback();
        } finally {
            connection.close();
        }
    }

}
