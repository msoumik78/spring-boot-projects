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
    return createStatement -> jdbcTemplate.update("Create table tasks " +
      "(id bigint auto_increment, task_name VARCHAR(50), " +
      "task_description VARCHAR(100), task_completion_percentage int, task_end_date date)");
  }

}
