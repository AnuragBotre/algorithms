package com.trendcore;

import com.trendcore.servlet.JettyServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 */
public class App {

    public static void main(String[] args) throws Exception {

        App app = new App();

        Server server = new Server(8080);



        //app.addServletHandler(server);

        app.addSessionIdManager(server);

        String rootPath = App.class.getClassLoader().getResource(".").toString();
        WebAppContext webapp = new WebAppContext(rootPath+ "../../src/main/webapp/","");
        server.setHandler(webapp);

        //app.addSessionHandlerToContext(webapp);

        server.start();

        server.join();

        //app.setUpJMX();
    }

    private void addSessionHandlerToContext(WebAppContext webapp) {
        HashSessionManager manager = new HashSessionManager();
        SessionHandler sessions = new SessionHandler(manager);
        webapp.setHandler(sessions);
    }

    private void addSessionIdManager(Server server) {
        HashSessionIdManager idmanager = new HashSessionIdManager();
        server.setSessionIdManager(idmanager);
    }

    private void addServletHandler(Server server) {
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(JettyServlet.class, "/*");
        addSessionHandlerToContext(handler);
    }

    private void addSessionHandlerToContext(ServletHandler context) {
        HashSessionManager manager = new HashSessionManager();
        SessionHandler sessions = new SessionHandler(manager);
        context.setHandler(sessions);
    }

    /*private void setUpJMX() {
        MBeanContainer mbContainer = new MBeanContainer(
                ManagementFactory.getPlatformMBeanServer());
        server.addBean(mbContainer);
    }*/
}
