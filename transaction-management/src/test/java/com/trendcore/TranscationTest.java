package com.trendcore;

import com.trendcore.transaction.TransactionHandler;
import org.junit.Test;

public class TranscationTest {

    @Test
    public void basicTransactionSetup() throws Exception {
        TransactionHandler t = new TransactionHandler(new MockDataSource());

        t.run(connection -> {
            System.out.println("inserting into tables..");
        });
    }
}
