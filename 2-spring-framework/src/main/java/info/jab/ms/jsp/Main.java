package info.jab.ms.jsp;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.File;

@EnableWebMvc
public class Main {

	public static void main(String[] args) throws Exception {
		startTomcat();
	}

	static void startTomcat() throws Exception {
		Tomcat tomcat = new Tomcat();
		tomcat.getConnector().setPort(8080);

		/*
		Context context = tomcat.addContext("", System.getProperty("java.io.tmpdir"));

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

		 */

		try {
			tomcat.start();
			tomcat.getServer().await();
		} catch (LifecycleException e) {
			System.out.println("Katakroker");
		}
	}

	@Configuration
	public class MyWebApplicationInitializer implements WebApplicationInitializer {

		@PostConstruct
		private void postConstruct() {
			System.out.println("Running Spring Config 2");
		}
		@Override
		public void onStartup(ServletContext servletContext) {

			// Load Spring web application configuration
			AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
			context.register(SpringConfig.class);

			// Create and register the DispatcherServlet
			DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
			ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
			registration.setLoadOnStartup(1);
			registration.addMapping("/");
		}
	}

	@Configuration
	static class SpringConfig {

		@Controller
		public static class MyRestController {

			@GetMapping("/hello2")
			public String hello() {
				return "hello2";
			}

			@PostConstruct
			private void postConstruct() {
				System.out.println("Running RestController");
			}
		}

		@Bean
		public ViewResolver viewResolver() {
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class);
			viewResolver.setPrefix("/WEB-INF/");
			viewResolver.setSuffix(".jsp");
			return viewResolver;
		}

		@PostConstruct
		private void postConstruct() {
			System.out.println("Running Spring Config");
		}
	}

}
