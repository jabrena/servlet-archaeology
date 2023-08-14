package com.mycompany.app;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class Main {

    public static void main(String[] args) throws Exception {
        int port = 8081; // specify the desired port

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("."); // Set the base directory (optional)

        // Set up the connector
        Connector connector = new Connector();
        connector.setPort(port);
        tomcat.getService().addConnector(connector);

        // Add a servlet context and configure the Spring Boot application
        Context context = tomcat.addContext("", null);

        
        // Create and configure the DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setContextClass(AnnotationConfigWebApplicationContext.class); // Use AnnotationConfigWebApplicationContext

        // Manually create an AnnotationConfigApplicationContext and register the configuration class
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        
        // Set the DispatcherServlet's application context
        dispatcherServlet.setApplicationContext(applicationContext);

        // Register the DispatcherServlet as a servlet
        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.start();
        tomcat.getServer().await();
    }

    @RestController
    public static class MyRestController {

        @GetMapping("/hello")
        public String hello() {
            return "Hello, Spring Boot!";
        }

        @PostConstruct
        private void postConstruct() {
            System.out.println("Running RestController");
        }
    }

    @Configuration
    @ComponentScan(basePackages = "com.mycompany.app")
    public static class SpringConfig {

        @PostConstruct
        private void postConstruct() {
            System.out.println("Running");
        }
    }
}
