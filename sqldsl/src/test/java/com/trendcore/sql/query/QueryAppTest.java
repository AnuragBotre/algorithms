package com.trendcore.sql.query;

import com.trendcore.sql.Column;
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


    @Test
    public void selectQuerySyntax() {
        //Select col1,col2,col3 from table1 as t1 inner join table2 as t2
        //on t1.col1 = t2.col2
        //where t1.col1 >= <criteria> and t2.col2 <= <criteria>

        //select(col1,col2).from(table1)
        //select(col1,col2).from(as(table1,"t"))
        //select(col1,col2).from(as(table1,"t"))
        Student s = new Student();
        select(s.ID,s.NAME).from(s);
        select(s.ID,s.NAME).from(as(s,"t"));

        //joins
        //select(s.ID,s.NAME).from(join());

        //select(col1,col2).from(as(table1,"t"))
        //where t1.col1 >= <criteria> and t2.col2 <= <criteria>
    }

}
