package info.jab.ms.alternative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.ServletContext;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("info.jab.ms.alternative") // Specify the package to scan for components
public class Main2 {

    public static void main2(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Main2.class);

        // No need to manually create a DispatcherServlet and ServletRegistrationBean
        // Spring MVC will automatically initialize the DispatcherServlet

        // Initialize the Spring application context
        appContext.refresh();
    }

    @Configuration
    public static class WebConfig implements WebMvcConfigurer, ServletContextAware {

        private ServletContext servletContext;

        @Autowired
        public void setServletContext(ServletContext servletContext) {
            this.servletContext = servletContext;
        }

        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.add(new MappingJackson2HttpMessageConverter());
        }

        @Override
        public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
        }

        // Configure resource handling
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**")
                    .addResourceLocations("/resources/"); // Set the location of your static resources
        }
    }

    /*
    public static class SpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
        @Override
        protected Class[] getServletConfigClasses() {
            return new Class[] { WebConfig.class };
        }
 
        @Override
        protected String[] getServletMappings() {
            return new String[] { "/" };
        }
    
        @Override
        protected Class[] getRootConfigClasses() {
            return new Class[] {};
        }
    }
    */

    @RestController
    public static class HelloController {

        @GetMapping("/hello")
        @ResponseBody
        public String hello() {
            return "Hello, Spring MVC!";
        }
    }
}

