# Makefile for Project Servlet Archaeology
# Provides recipes for verifying the project and running examples.

# Use .ONESHELL to execute a target recipe as a single SHELL call
.ONESHELL:

# tell make that these targets are NOT meant to be files/directories
.PHONY: project-verify servlet spring-framework spring-boot

# Variables
MVN_CMD = ./mvnw
MAIN_CLASS = info.jab.ms.Main

run-example = $(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="$(MAIN_CLASS)" -pl $1

help: # *Show all recipes from this project*
	@grep -E '^[a-zA-Z0-9 -]+:.*#'  Makefile | sort | while read -r l; do printf "\033[1;32m$$(echo $$l | cut -f 1 -d':')\033[00m:$$(echo $$l | cut -f 2- -d'#')\n"; done

project-verify: # Verify the whole project
	$(MVN_CMD) --batch-mode --no-transfer-progress clean verify
servlet: # Run an example using Apache tomcat and a Servlet
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.Main" -pl 1-servlet
spring-framework-jsp: # Run an example using Spring Framework & JSP
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.jsp.Main" -pl 2-spring-framework
spring-framework-mvc: # Run an example using Spring Framework MVC
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.mvc.Main" -pl 2-spring-framework
spring-framework-mvcfn: # Run an example using Spring Framework MVC with functional style
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.mvcfn.Main" -pl 2-spring-framework
spring-boot-1: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot1.Main" -pl 3-spring-boot
spring-boot-2: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot2.Main" -pl 3-spring-boot
spring-boot-3: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot3.Main" -pl 3-spring-boot
spring-boot-4: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot4.Main" -pl 3-spring-boot
spring-boot-5: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot5.Main" -pl 3-spring-boot
spring-boot-6: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot6.Main" -pl 3-spring-boot
spring-boot-7: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot7.Main" -pl 3-spring-boot
spring-boot-8: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot8.Main" -pl 3-spring-boot
spring-boot-9: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot9.Main" -pl 3-spring-boot
spring-boot-10: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot10.Main" -pl 3-spring-boot
spring-boot-11: # Run an example using Spring Boot
	$(MVN_CMD) --batch-mode --no-transfer-progress compile exec:java -Dexec.mainClass="info.jab.ms.boot11.Main" -pl 3-spring-boot
