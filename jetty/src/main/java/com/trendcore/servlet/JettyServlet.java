package com.trendcore.servlet;

import com.trendcore.Profile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Anurag on 1/1/2017.
 */
public class JettyServlet extends HttpServlet{

    @Override
    @Profile(category = "HTTP_SERVLET")
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello from HelloServlet This is modified</h1>");

        HttpSession session = request.getSession(false);

        if(session == null){
            session = request.getSession(true);
            session.setAttribute("username","Anurag");
        }else{
            response.getWriter().println(session.getAttribute("username"));
        }
    }

}
