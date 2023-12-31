package info.jab.ms.mvc;

import java.io.File;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import jakarta.annotation.PostConstruct;

public class Main {

    public static void main(String[] args) throws Exception {

        Connector connector = new Connector();
        connector.setPort(8080);

        Tomcat tomcat = new Tomcat();
        tomcat.getService().addConnector(connector);

        File base = new File(System.getProperty("java.io.tmpdir"));
        Context context = tomcat.addContext("", base.getAbsolutePath());

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(SpringConfig.class);
        appContext.refresh();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
        Wrapper wrapper = context.createWrapper();
        wrapper.setName("dispatcherServlet");
        wrapper.setServlet(dispatcherServlet);
        context.addChild(wrapper);
        wrapper.setLoadOnStartup(1);
        wrapper.addMapping("/");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            System.out.println("Katakroker");
        }
    }

    @RestController
    public static class MyRestController {

        @GetMapping("/hello")
        public String hello() {
            return "Hello world";
        }

        @PostConstruct
        private void postConstruct() {
            System.out.println("Running RestController");
        }
    }

    @Configuration
    @ComponentScan(basePackages = "info.jab.ms")
    public static class SpringConfig {

        @PostConstruct
        private void postConstruct() {
            System.out.println("Running");
        }
    }
}
