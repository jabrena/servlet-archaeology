package info.jab.ms.boot11;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11Nio2Protocol;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        File base = new File(System.getProperty("java.io.tmpdir"));
        Context context = tomcat.addContext("", base.getAbsolutePath());

        context.addServletContainerInitializer((c, ctx) -> {
            HelloServlet helloServlet = new HelloServlet();
            ctx.addServlet("helloServlet", helloServlet).addMapping("/hello");
        }, Collections.emptySet());

        tomcat.start();

        Connector connector = new Connector(new Http11Nio2Protocol());
        connector.setPort(8080);
        tomcat.setConnector(connector);
    }
}

class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Hello world");
    }
}