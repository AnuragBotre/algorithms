package com.trendcore.sql.query;

import com.trendcore.sql.Student;
import org.junit.Test;

import java.util.Date;

import static com.trendcore.sql.query.QueryApp.Select.select;
import static com.trendcore.sql.query.QueryApp.as;

public class QueryAppTest {

    @Test
    public void selectQuery() throws Exception {
        select().from(as("t")).where();
    }




}
