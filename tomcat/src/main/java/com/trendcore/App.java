package com.trendcore;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);


        String webappDirLocation = App.class.getClassLoader().getResource(".").getPath() + "../../src/main/webapp/";

        System.out.println(webappDirLocation);

        tomcat.addWebapp("/", webappDirLocation);



        tomcat.start();
        tomcat.getServer().await();
    }
}
