package com.trendcore.sample.application.approach2;

import com.trendcore.sample.application.approach1.MockHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Application {

    Map<String, Function<HttpServletRequest,Context>> requestHandler = new HashMap<>();

    Context context;

    public static class Context {
        private Function mysql;
        private Function oracle;
        private Function mssql;

        public Context() {

        }

        public Context(Function mysql) {

        }

        public Context(Function mysql, Function oracle, Function mssql) {

        }
    }

    public static void main(String[] args) {
        Application application = new Application();

        application.onRequest("/user/add", new Function<HttpServletRequest, Context>() {
            @Override
            public Context apply(HttpServletRequest c) {
                Context context = new Context();
                context.mysql = o -> {
                    System.out.println("Inside mysql ");
                    return null;
                };
                return context;
            }
        });

        application.start();
    }

    private void start() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = "";

            while (!s.equals("exit")) {
                s = bufferedReader.readLine();
                Function<HttpServletRequest, Context> function = this.requestHandler.get(s);
                Context context = function.apply(new MockHttpRequest());
                context.mysql.apply(new MockHttpRequest());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onRequest(String s, Function<HttpServletRequest, Context> function) {
        requestHandler.put(s, function);
    }

}