package com.trendcore.sample.application;

import com.trendcore.HikariDataSource;
import com.trendcore.SelectStream;
import com.trendcore.sample.application.approach1.DAO;
import com.trendcore.sample.application.approach1.DAOSpecs;
import com.trendcore.sample.application.approach1.MockHttpRequest;
import com.trendcore.sql.Column;
import com.trendcore.sql.Row;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.trendcore.sample.application.approach1.DefineApplication.application;

public class UserModule {

    public static class ActorResult {
        public static Column<Integer> actor_id;
        public static Column<String> first_name;
        public static Column<String> last_name;
        public static Column<Timestamp> last_update;
    }

    static
    {
        DAOSpecs mysqlDao = new DAOSpecs() {
            @Override
            public Stream getUsers() {
                System.out.println("Inside Mysql Implementation -> Get Users");
                return null;
            }

            @Override
            public void insertUsers() {
                System.out.println("Inside Mysql Implementation -> Insert Users");
            }
        };

        Module module = application.defineModule("module1");
        module.baseUrl("/user");
        module.serve("/add", (Function<HttpServletRequest, Stream>) req -> {
            /*SelectCode s = new SelectCode();
            Supplier<Stream<Row>> supplier = s.executeMysql("select * from address a where a.address = ?", 1);

            return supplier.get();*/
            return mysqlDao.getUsers();
        });

        application.registerDao("mysql", mysqlDao);

        application.registerDao("oracle", new DAOSpecs() {
            @Override
            public Stream getUsers() {
                System.out.println("Inside Oracle Implementation -> Get Users");
                return null;
            }

            @Override
            public void insertUsers() {
                System.out.println("Inside Oracle Implementation -> Insert Users");
            }
        });
    }

    public Stream getUsers() {
        System.out.println("This is common method...");
        return null;
    }

    public static void main(String[] args) {
        UserModule userModule = new UserModule();
        application.invoke("/user/add",new MockHttpRequest());
    }
}
