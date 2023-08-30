package info.jab.ms.boot9;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {

	public static void main(String[] args) {
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		applicationContext.registerBean(MyRestController.class);
		applicationContext.refresh();

		WebServer webServer = serverFactory.getWebServer(servletContext ->
				servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext))
				.addMapping("/"));
		webServer.start();
	}
}

@RestController
class MyRestController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello world";
	}

	//Not working here ¯\_ (ツ)_/¯
	@PostConstruct
	private void postConstruct() {
		System.out.println("Running RestController");
	}
}