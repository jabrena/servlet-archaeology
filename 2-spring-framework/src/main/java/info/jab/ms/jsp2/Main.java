package info.jab.ms.jsp2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.IOException;

//TODO Remove autoconfiguration
@EnableAutoConfiguration
@ComponentScan
@ServletComponentScan
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Service
    public static class GreetingService {

        public String greet(String name) {
            return "Hello " + name + "!";
        }

    }

    @WebServlet(urlPatterns = "/hello")
    public static class HelloServlet extends HttpServlet {

        @Autowired
        GreetingService greetingService;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String name = req.getParameter("name");
            String greeting = greetingService.greet(name == null ? "World" : name);
            req.setAttribute("greeting", greeting);
            req.getRequestDispatcher("WEB-INF/hello.jsp").forward(req, resp);
        }
    }

}
