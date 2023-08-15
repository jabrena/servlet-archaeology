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
	$(MVN_CMD) clean verify
servlet: # Run an example using Apache tomcat and a Servlet
	$(call run-example,1-servlet)
spring-framework: # Run an example using Spring Framework only
	$(call run-example,2-spring-framework)
spring-boot: # Run an example using Spring Boot
	$(call run-example,3-spring-boot)
