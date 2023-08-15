# Servlet Archaeology

Learning about how Spring interacts with a Servlet container like Tomcat.

```bash
mvn clean verify
mvn compile exec:java -Dexec.mainClass="info.jab.ms.Main" -pl 1-servlet
mvn compile exec:java -Dexec.mainClass="info.jab.ms.Main" -pl 2-spring-framework
mvn compile exec:java -Dexec.mainClass="info.jab.ms.Main" -pl 3-spring-boot

curl http://localhost:8080/hello
kill $(lsof -t -i:8080)
```
