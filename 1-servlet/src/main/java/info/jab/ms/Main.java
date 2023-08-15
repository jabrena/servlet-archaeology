package info.jab.ms;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Main {

    public static void main(String[] args) {

        Connector connector = new Connector();
        connector.setPort(8080);

        Tomcat tomcat = new Tomcat();
        tomcat.getService().addConnector(connector);

        File base = new File(System.getProperty("java.io.tmpdir"));
        Context context = tomcat.addContext("/", base.getAbsolutePath());

        HttpServlet myServlet = new MyServlet();
        Wrapper servletWrapper = Tomcat.addServlet(context, "MyServlet", myServlet);
        servletWrapper.addMapping("/hello");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}

class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("Hello world");
    }
}
