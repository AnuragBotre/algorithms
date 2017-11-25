package com.trendcore.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anurag on 1/21/2017.
 */
public class CentralController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if(req.getRequestURL().toString().endsWith(".js")){
            super.doGet(req,resp);
        }else{
            //process response
            resp.getOutputStream().print("<html><body>");
            resp.getOutputStream().print(String.valueOf(req.getRequestURL()));
            resp.getOutputStream().print("<br/>");
            resp.getOutputStream().print(String.valueOf(req.getRequestURL()));
            resp.getOutputStream().print("<br/>");
            resp.getOutputStream().print("</body><html>");
        }
    }
}
