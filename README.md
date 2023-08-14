# Servlet Archaeology

Learning about how Spring interacts with a Servlet container like Tomcat.

```bash
mvn compile exec:java -Dexec.mainClass="com.mycompany.app.Main" -pl myServlet1
mvn compile exec:java -Dexec.mainClass="com.mycompany.app.Main" -pl myServlet2
mvn compile exec:java -Dexec.mainClass="com.mycompany.app.Main" -pl myServlet3

lsof -i :8081
curl http://localhost:8081/hello
curl http://localhost:8080/hello

```
