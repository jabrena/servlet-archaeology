# Servlet Archaeology

Learning about how Spring interacts with a Servlet container like Tomcat.

```bash
mvn compile exec:java -Dexec.mainClass="com.mycompany.app.Main" -pl myServlet1
mvn compile exec:java -Dexec.mainClass="com.mycompany.app.Main" -pl myServlet2
mvn compile exec:java -Dexec.mainClass="com.mycompany.app.Main" -pl myServlet3

curl http://localhost:8080/hello
kill $(lsof -t -i:8080)
```
