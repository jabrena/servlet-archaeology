package info.jab.ms.boot6;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Import({ApplicationConfiguration.class})
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Main.class)
                .build()
                .run(args);
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                .GET("/hello", request -> ServerResponse.ok().body("Hello world"))
                .build();
    }
}

@EnableWebMvc
@Configuration
@Import({
        ServletWebServerFactoryAutoConfiguration.class,
        DispatcherServletAutoConfiguration.class,
})
class ApplicationConfiguration {}