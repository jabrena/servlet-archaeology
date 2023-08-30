package info.jab.ms.boot4;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@Import({
		HttpMessageConvertersAutoConfiguration.class,
		DispatcherServletAutoConfiguration.class,
		ServletWebServerFactoryAutoConfiguration.class,
		WebMvcAutoConfiguration.class })
public class Main {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Main.class).run(args);
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