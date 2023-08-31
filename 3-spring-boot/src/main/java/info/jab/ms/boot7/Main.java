package info.jab.ms.boot7;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

@ComponentScan
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
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