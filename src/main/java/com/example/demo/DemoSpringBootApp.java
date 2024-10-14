package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DemoSpringBootApp {
  public static void main(String[] args) {
    SpringApplication.run(DemoSpringBootApp.class, args);
  }

  @Bean
  public CommandLineRunner createTables(JdbcTemplate jdbcTemplate) {
    return createStatement -> jdbcTemplate.update("Create table users " +
      "(id bigint auto_increment,user_name VARCHAR(50), " +
      "password VARCHAR(100), full_name  VARCHAR(100))");
  }

}
