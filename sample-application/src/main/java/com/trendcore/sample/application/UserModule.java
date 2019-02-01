package com.trendcore.sample.application;

import com.trendcore.HikariDataSource;
import com.trendcore.SelectStream;
import com.trendcore.sql.Column;
import com.trendcore.sql.Row;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.function.Function;
import java.util.stream.Stream;

public class UserModule {

    public static class ActorResult {
        public static Column<Integer> actor_id;
        public static Column<String> first_name;
        public static Column<String> last_name;
        public static Column<Timestamp> last_update;
    }

    {
        Module module = Application.defineModule("module1");
        module.baseUrl("/user");
        module.serve("/add", (Function<HttpServletRequest, Stream>) req -> {
            System.out.println("serving /");

            SelectCode s = new SelectCode();
            Stream<Row> stream = s.execute("select * from address a where a.address = ?", 1);

            return stream;
        });
    }
}
