package com.trendcore.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Anurag on 1/21/2017.
 */
public class ResourceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
