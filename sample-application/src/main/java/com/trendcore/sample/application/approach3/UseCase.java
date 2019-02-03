package com.trendcore.sample.application.approach3;

import com.trendcore.Actor;
import com.trendcore.sample.application.approach1.MockHttpRequest;
import com.trendcore.sql.Row;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

public class UseCase {

    public Stream<Row> dataRetrieval(HttpServletRequest request) {

        Algorithm<HttpServletRequest,Stream<Row>> algorithm = new Algorithm(request);

        Stream<Row> stream = algorithm.map(req -> req.getParameterMap())
                .fetch(stringMap -> new Algorithm.DatabaseContext().mysql(this::getMysqlQuery))
                .execute();

        return stream;
    }

    private Algorithm.QueryContext getMysqlQuery() {
        return new Algorithm.QueryContext("select * from sakila.actor where actor_id in (?,?,?)",1,2,3);
    }

    public static void main(String[] args) {
        UseCase useCase = new UseCase();
        Stream<Row> rowStream = useCase.dataRetrieval(new MockHttpRequest());

        rowStream.forEach(row -> System.out.println(row.get(Actor.ID)));

    }

}
