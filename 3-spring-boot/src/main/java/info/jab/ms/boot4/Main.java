package info.jab.ms.boot4;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Import({
		DispatcherServletAutoConfiguration.class,
		ServletWebServerFactoryAutoConfiguration.class,
		WebMvcAutoConfiguration.class })
public class Main {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Main.class).run(args);
	}

	@Bean
	RouterFunction<ServerResponse> routes() {
		return RouterFunctions.route()
				.GET("/hello", request -> ServerResponse.ok().body("Hello world"))
				.build();
	}
}