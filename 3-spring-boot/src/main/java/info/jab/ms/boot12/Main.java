package info.jab.ms.boot12;

import jakarta.annotation.PostConstruct;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import java.io.File;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        File base = new File(System.getProperty("java.io.tmpdir"));
        Context context = tomcat.addContext("", base.getAbsolutePath());

        WebApplicationContext springContext = getApplicationContext();

        context.addServletContainerInitializer((c, ctx) -> {
            for (ServletRegistrationBean registrationBean : springContext.getBeansOfType(ServletRegistrationBean.class).values()) {
                registrationBean.onStartup(ctx);
            }
        }, Collections.emptySet());

        tomcat.start();

        Connector connector = new Connector(new Http11Nio2Protocol());
        connector.setPort(8080);
        tomcat.setConnector(connector);
    }

    public static WebApplicationContext getApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        context.refresh();
        return context;
    }
}

@ComponentScan
@Configuration
class WebConfig {

    @Bean
    public DispatcherServlet dispatcherServlet(WebApplicationContext applicationContext) {
        return new DispatcherServlet(applicationContext);
    }

    @Bean
    public DispatcherServletRegistrationBean registrationBean(DispatcherServlet dispatcherServlet) {
        return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
    }
}

@RestController
class MyRestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println("Running RestController");
    }
}