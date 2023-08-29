package info.jab.ms.jsp;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        //appContext.register(SpringConfig.class);
        appContext.refresh();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        // Registering Dispatcher Servlet with Servlet
        // Context
        ServletRegistration.Dynamic myCustomDispatcherServlet = servletContext.addServlet("myDispatcherServlet", dispatcherServlet);

        // Create and configure the servlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet("helloServlet", new HelloServlet());
        servlet.addMapping("/hello");
    }
}