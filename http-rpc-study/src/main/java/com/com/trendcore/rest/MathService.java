package com.com.trendcore.rest;


import org.httprpc.RequestMethod;
import org.httprpc.ResourcePath;
import org.httprpc.WebService;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(urlPatterns={"/math/*"})
public class MathService extends WebService {

    @RequestMethod("GET")
    @ResourcePath("sum")
    // - /math/sum?a=2&b=4 - hit this url in the browser
    public double getSum(double a, double b) {
        return a + b;
    }

    @RequestMethod("GET")
    @ResourcePath("sum")
    // - /math/sum?values=1&values=2&values=3
    public double getSum(List<Double> values) {
        double total = 0;

        for (double value : values) {
            total += value;
        }

        return total;
    }
}
