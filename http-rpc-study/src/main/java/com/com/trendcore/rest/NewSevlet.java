package com.com.trendcore.rest;

import com.trendcore.HikariDataSource;
import com.trendcore.SelectStream;
import com.trendcore.sql.Column;
import com.trendcore.sql.Row;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

@WebServlet(urlPatterns={"/newServlet/*"})
public class NewSevlet extends HttpServlet {

    private final DataSource dataSource;

    public NewSevlet() {
        dataSource = HikariDataSource.get().getDataSource();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SelectStream select = new SelectStream(dataSource);
        Stream<Row> stream = select.stream("select * from address a where a.address = ?", 1);

        PrintWriter writer = resp.getWriter();

        stream.forEach(actorRow -> {
            Column id = new Column();
            id.setIndex(1);
            writer.println(actorRow.get(id));
        });
    }
}
