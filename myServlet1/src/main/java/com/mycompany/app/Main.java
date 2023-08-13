package com.mycompany.app;

import java.io.IOException;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Main {

    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();
        int port = 8081;
        
        Connector connector = new Connector();
        connector.setPort(port);
        tomcat.getService().addConnector(connector);

        Context context = tomcat.addContext("", null);
        HttpServlet myServlet = new MyServlet();
        Wrapper servletWrapper = Tomcat.addServlet(context, "MyServlet", myServlet);
        servletWrapper.addMapping("/hello");

        tomcat.start();
        tomcat.getServer().await();
    }
}

class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("Hello, world!");
    }
}
