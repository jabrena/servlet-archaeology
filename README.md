# Servlet Archaeology

[![CI Builds](https://github.com/jabrena/servlet-archaeology/actions/workflows/build.yaml/badge.svg)](https://github.com/jabrena/servlet-archaeology/actions/workflows/build.yaml)

## Motivation
Spring boot is a wonderful framework around Spring Framework and make the life easier for any Software engineer daily but
sometimes if you want to understand some decision taken by Spring boot, you need to scratch the surface and this is the purpose of this repository.

I would like to underdand some details about the interaction between a Servlet container like Tomcat and Spring Framework.

```bash
mvn clean verify
mvn compile exec:java -Dexec.mainClass="info.jab.ms.Main" -pl 1-servlet
mvn compile exec:java -Dexec.mainClass="info.jab.ms.Main" -pl 2-spring-framework
mvn compile exec:java -Dexec.mainClass="info.jab.ms.Main" -pl 3-spring-boot

curl http://localhost:8080/hello
kill $(lsof -t -i:8080)
```
## References

- https://tomcat.apache.org/
- https://docs.spring.io/spring-framework/reference/web/webmvc.html
- https://dunwu.github.io/spring-tutorial/pages/20287b/#%E7%AE%80%E4%BB%8B
- https://github.com/dsyer/spring-boot-micro-apps/tree/main
- https://github.com/spring-projects-experimental/spring-boot-thin-launcher