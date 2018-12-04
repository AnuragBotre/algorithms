package com.trendcore;

import com.trendcore.sql.Student;
import com.trendcore.transaction.TransactionHandler;
import org.junit.Test;

import java.util.Date;

public class TranscationTest {

    @Test
    public void basicTransactionSetup() throws Exception {
        TransactionHandler t = new TransactionHandler(new MockDataSource());

        t.execute(connection -> {
            System.out.println("inserting into tables..");
        });
    }

    @Test
    public void insertSingleTableData() throws Exception {
        Student student = new Student();
        student.val(Student.ID , 1 );
        student.val(Student.NAME , "Anurag" );
        student.val(Student.BIRTHDATE , new Date());
        TransactionHandler t = new TransactionHandler(new MockDataSource());

        t.execute(connection -> {
            DefaultInsertCommand d = new DefaultInsertCommand(connection);
            d.insert(student);
        });
    }
}
