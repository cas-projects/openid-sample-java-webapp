package org.ardias.openid;


import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;




public class Tester {

    public static void main(String...args) {

        Tester t = new Tester();
        try {
            t.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void run() throws Exception {
        Properties props = new Properties();

        props.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));

        OpenIdService resource = new OpenIdService(props);
        Set<Object> resourceSingletons = new HashSet<>();
        resourceSingletons.add(resource);
        CXFNonSpringJaxrsServlet context = new CXFNonSpringJaxrsServlet(resourceSingletons);


        ServletHolder sh = new ServletHolder(context);
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.addServlet(sh, "/*");
        contextHandler.setContextPath("/");

        Server server = new Server(9000);
        server.setHandler(contextHandler);

        System.out.println(">>> STARTING JETTY SERVER, PRESS ANY KEY TO STOP");
        server.start();
        while (System.in.available() == 0) {
            Thread.sleep(1000);
        }
        server.stop();
        server.join();
    }
}
