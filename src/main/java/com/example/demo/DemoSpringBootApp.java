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
    return createStatement -> {
      jdbcTemplate.update("Create table tasks " +
        "(id bigint auto_increment, task_name VARCHAR(50), " +
        "task_description VARCHAR(100), task_completion_percentage int, task_end_date date, user_id int)");

      jdbcTemplate.update("Create table users " +
        "(user_id bigint auto_increment, user_name VARCHAR(50), " +
        "user_password VARCHAR(200), full_name VARCHAR(100) )");
    };
  }

}
