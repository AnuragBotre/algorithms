package com.trendcore;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SelectTest {

    @Test
    public void selectDefinition() {

        String json = "{\"test\":\"1\"}";

        Query query = new Query();

        Object select = query.
                        select.
                        from().
                        innerJoin().
                        leftJoin().
                        where().
                        groupBy().
                        having();
    }

    @Test
    public void usingStreams() {
        //https://speedment.github.io/speedment-doc/speedment_examples.html#sql-equivalences

        //https://speedment.github.io/speedment-doc/introduction.html

        //https://jaxenter.com/java-8-goodie-sql-resultset-streams-107756.html

        //https://blog.jooq.org/2015/08/13/common-sql-clauses-and-their-equivalents-in-java-8-streams/

        //https://www.javacodegeeks.com/2018/05/java-stream-orm-now-with-joins.html

        //language=MYSQL-SQL
        String sql = "select * from address a where a.address = 1 ";

        //language=JSON
        String json = "{\n" +
                "  \"temp\" : 1,\n" +
                "  \"config\" : 1,\n" +
                "  \"arr\" : []\n" +
                "}";

        List list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.stream();
    }
}
