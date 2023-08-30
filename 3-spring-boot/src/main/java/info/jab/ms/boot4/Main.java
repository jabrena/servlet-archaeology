package info.jab.ms.boot4;

import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Import({
		HttpMessageConvertersAutoConfiguration.class,
		DispatcherServletAutoConfiguration.class,
		ServletWebServerFactoryAutoConfiguration.class,
		WebMvcAutoConfiguration.class })
@RestController
public class Main {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Main.class).run(args);
	}

	@GetMapping("/hello")
	public String home() {
		return "Hello world";
	}
}