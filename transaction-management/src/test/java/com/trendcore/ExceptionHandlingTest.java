package com.trendcore;

import com.trendcore.exception.SystemException;
import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.stream.Stream;

import static com.trendcore.DatabaseErrorCode.DATA_ACCESS_EXCEPTION;

public class ExceptionHandlingTest {

    private DataSource dataSource;

    @Before
    public void setUp() {
        dataSource = HikariDataSource.get().getDataSource();
    }

    @Test
    public void exceptionRaised() {

        CodeExecutor.execute(() -> {
            SelectStream select = new SelectStream(dataSource);

            Stream<Row> stream = select.stream("select * from address a where a.address", 1);

            stream.forEach(actorRow -> {
                Column id = new Column();
                id.setIndex(1);
                System.out.println(actorRow.get(id));
            });
        }, SystemException.class).handle(e -> Assert.assertTrue(true));
    }

    @Test
    public void noExceptionShouldBeRaised() {
        CodeExecutor.execute(() -> {
            SelectStream select = new SelectStream(dataSource);

            Stream<Row> stream = select.stream("select * from address a where a.address = ?", 1);

            stream.forEach(actorRow -> {
                Column id = new Column();
                id.setIndex(1);
                System.out.println(actorRow.get(id));
            });
        }, SystemException.class).handle(e -> Assert.assertTrue(false));
        Assert.assertTrue(true);
    }

    @Test
    public void handlingException() {
        CodeExecutor.execute(() -> {
            SelectStream select = new SelectStream(dataSource);
            Stream<Row> stream = select.stream("select * from address a where a.address", 1);
            stream.forEach(actorRow -> {
                Column id = new Column();
                id.setIndex(1);
                System.out.println(actorRow.get(id));
            });
        }, SystemException.class).handle(e -> {
            if(e.getErrorCode() != null){
                switch (e.getErrorCode().getErrorCode()){
                    case DATA_ACCESS_EXCEPTION:
                        System.out.println("Error occurred while accessing data..");
                        break;
                    default:
                        System.out.println("Error occurred while fetching data.");
                        break;
                }
            }

        });
    }
}
