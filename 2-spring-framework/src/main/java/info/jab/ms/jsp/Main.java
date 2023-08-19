package info.jab.ms.jsp;

import java.util.Optional;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

public class Main {

	public static void main(String[] args) throws Exception {
		startTomcat();
	}

	static StaticWebApplicationContext applicationContext(RouterFunction<ServerResponse> routes) {
		StaticWebApplicationContext applicationContext = new StaticWebApplicationContext();
		// Add JSP View Resolver
		applicationContext.registerBean(ViewResolver.class, () -> {
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class);
			viewResolver.setPrefix("/WEB-INF/views/"); // Path to JSP files
			viewResolver.setSuffix(".jsp");
			return viewResolver;
		});
		return applicationContext;
	}

	static void startTomcat() throws Exception {
		Tomcat tomcat = new Tomcat();
		int port = Optional.ofNullable(System.getenv("PORT")).map(Integer::parseInt).orElse(8080);
		tomcat.getConnector().setPort(port);
		Context context = tomcat.addContext("", System.getProperty("java.io.tmpdir"));
		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext(routes()));
		Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet).addMapping("/");
		tomcat.start();
		tomcat.getServer().await();
	}

	static RouterFunction<ServerResponse> routes() {
		return RouterFunctions.route()
				.GET("/jsp", request -> ServerResponse.ok().render("hello")) // Handle JSP rendering
				.build();
	}

}
