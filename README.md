# Servlet Archaeology

[![CI Builds](https://github.com/jabrena/servlet-archaeology/actions/workflows/build.yaml/badge.svg)](https://github.com/jabrena/servlet-archaeology/actions/workflows/build.yaml)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=jabrena_servlet-archaeology)

## Motivation
Spring Boot is an exceptional solution built upon the foundation of the Spring Framework, greatly simplifying the daily tasks of software engineers. However, there are instances when comprehending certain decisions made within Spring Boot requires delving beneath the surface. This repository serves precisely that purpose – shedding light on these intricacies.

## Servlet evolution

Presently, I am keen on gaining a deeper understanding of the dynamic interplay between a Servlet container, such as Tomcat, and the intricate mechanics of the Spring Framework.

```bash
make help
make servlet
make spring-framework-jsp2
make spring-framework-mvc
make spring-framework-mvcfn
make spring-boot

open http://localhost:8080/hello
curl http://localhost:8080/hello
kill $(lsof -t -i:8080)
```
## References

- https://tomcat.apache.org/
- https://docs.spring.io/spring-framework/reference/web/webmvc.html
- https://dunwu.github.io/spring-tutorial/pages/20287b/#%E7%AE%80%E4%BB%8B
- https://github.com/spring-projects/spring-boot/tree/main/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure 
- https://github.com/dsyer/spring-boot-micro-apps/tree/main
- https://github.com/spring-projects-experimental/spring-boot-thin-launcher