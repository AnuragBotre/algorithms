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

    @Test
    public void name() {
        //need to build query as below

        //select(t1.col1,t2.col1).from(t1).join(t2).on(t1.col1.eq(t2.col2) <Join Criteria>).where(t1.col.eq(1)).or(t2.col.eq(2)).and(t1.col.eq(4))

        /*select(

        ).from(
            //Option1
            table1.as(t1).leftJoin(
                    table2.as(t2),on(table1.col1.eq(table2.col2))
            ).innerJoin(
                table2.as(t4),on(table4.col.eq(table2.col2))
            )

        ).where(
            //need to consider this parameter as first
            t1.col1.eq(t2.col2),
            and(
                t1.col1.eq(1),t2.col1.eq(2)
            ),
            or(

            )

            //option2
            //how to deal with this case
            //
            t1.col1.eq(t2.col2).and()
        ).groupBy(
            t1.col1,
            t2.col2
        ).having(

        ).orderBy(
            t1.col1.asc,
            t2.col2.desc
        ).limit().offset();*/

        //for where there is another option is there
        //need to handle this case where (c1 = 1 and c2 = 2) or c3 = 3
        /*where(t1.col1.eq(1))
                .and(
                    t1.col2.eq(2)

                    //exp
                    exp(t1.col2.eq(2) ).and(t1.col3.eq(t4.col4))

                ).or(
                    t1.
                )*/

    }
}
