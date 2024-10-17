package com.example.demo;

import com.example.demo.model.LoginDetails;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = DemoSpringBootApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ToDoAppTest {

  @LocalServerPort
  private int port;

  TestRestTemplate restTemplate = new TestRestTemplate();
  HttpHeaders headers = new HttpHeaders();


  @Test
  void testIfContextLoads() {
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

  @Test
  void verify_if_user_creation_successful() {
    User user = new User();
    user.setUserName("msoumik78");
    user.setPassword("password");
    user.setFullName("Soumik Mukherjee");
    HttpEntity<User> entity = new HttpEntity<>(user, headers);

    ResponseEntity<String> response = restTemplate.exchange(
      createURLWithPort("/api/1/users"),
      HttpMethod.POST, entity, String.class);
    assertTrue(response.getStatusCode().is2xxSuccessful());
  }

  @Test
  void verify_if_jwt_returned_in_successful_login() {
    LoginDetails loginDetails = new LoginDetails();
    loginDetails.setUserName("msoumik78");
    loginDetails.setPassword("password");
    HttpEntity<LoginDetails> entity = new HttpEntity<>(loginDetails, headers);

    ResponseEntity<User> response = restTemplate.exchange(
      createURLWithPort("/api/1/user-credentials"),
      HttpMethod.POST, entity, User.class);
    assertTrue(response.getHeaders().containsKey("Authorization"));
  }

  @Test
  void verify_that_tasks_endpoint_need_jwt() {
    Task task = new Task();
    task.setTaskName("task name1");
    task.setTaskDescription("task descp1");

    HttpEntity<Task> entity = new HttpEntity<>(task, headers);

    ResponseEntity<Task> response = restTemplate.exchange(
      createURLWithPort("/api/1/tasks"),
      HttpMethod.POST, entity, Task.class);
    assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
  }



}
