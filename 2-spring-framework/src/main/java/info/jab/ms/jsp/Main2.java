package info.jab.ms.jsp;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main2 {

    public static void main(String[] args) throws Exception {
        // Create a Tomcat instance
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Create an instance of MyWebAppInitializer
        //MyWebAppInitializer initializer = new MyWebAppInitializer();

        // Configure Tomcat to use MyWebAppInitializer
        tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());
        //tomcat.add(initializer);

        // Start Tomcat
        tomcat.start();
        tomcat.getServer().await();
    }
}
