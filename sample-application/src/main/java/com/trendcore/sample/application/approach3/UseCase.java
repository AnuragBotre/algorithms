package com.trendcore.sample.application.approach3;

import com.trendcore.Actor;
import com.trendcore.sample.application.approach1.MockHttpRequest;
import com.trendcore.sql.Row;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Stream;

public class UseCase {

    private Algorithm<HttpServletRequest, Stream<Row>> algorithm;

    public void init(){
        algorithm = new Algorithm();
        algorithm.map(req -> req.getParameterMap())
                .fetch(stringMap -> new Algorithm.DatabaseContext().mysql(this::getMysqlQuery));
    }

    public Stream<Row> dataRetrieval(HttpServletRequest request) {

        Algorithm<HttpServletRequest,Stream<Row>> algorithm = new Algorithm();

        Optional<Stream<Row>> execute = algorithm.map(req -> req.getParameterMap())
                .fetch(stringMap -> new Algorithm.DatabaseContext().mysql(this::getMysqlQuery))
                .execute(request);

        execute.ifPresent(rowStream -> {

        });
        return null;
    }

    private Algorithm.QueryContext getMysqlQuery() {
        return new Algorithm.QueryContext("select * from actor where actor_id in (?,?,?)",1,2,3);
    }

    public static void main(String[] args) {
        UseCase useCase = new UseCase();
        useCase.init();

        Stream<Row> rowStream = useCase.dataRetrieval(new MockHttpRequest());

        rowStream.forEach(row -> System.out.println(row.get(Actor.ID)));

    }

}
