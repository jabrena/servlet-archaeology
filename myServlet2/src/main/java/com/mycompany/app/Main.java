package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @RestController
  public static class MyRestController {

    @GetMapping("/hello")
    public String hello() {
      return "Hello world";
    }

    @PostConstruct
    private void postConstruct() {
      System.out.println("Running RestController");
    }
  }

}