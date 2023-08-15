# .ONESHELL tells make to execute a target recipe as a single SHELL call
# (by default make would execute a recipe line by line in separate SHELL calls
.ONESHELL:

help: # *Show all recipes from this project* ↓↓↓
	@grep -E '^[a-zA-Z0-9 -]+:.*#'  Makefile | sort | while read -r l; do printf "\033[1;32m$$(echo $$l | cut -f 1 -d':')\033[00m:$$(echo $$l | cut -f 2- -d'#')\n"; done

project-verify: # Verify the whole project
	mvn clean verify
servlet: # Run an example using Apache tomcat and a Servlet
	mvn compile exec:java -Dexec.mainClass="info.jab.ms.Main" -pl 1-servlet
spring-framework: # Run an example using Spring Framework only
	mvn compile exec:java -Dexec.mainClass="info.jab.ms.Main" -pl 2-spring-framework
spring-boot: # Run an example using Spring Boot
	mvn compile exec:java -Dexec.mainClass="info.jab.ms.Main" -pl 3-spring-boot

# tell make that these targets are NOT meant to be files/directories
.PHONY: project-verify servlet spring-framework spring-boot