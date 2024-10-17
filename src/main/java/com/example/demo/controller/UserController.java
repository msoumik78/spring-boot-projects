package com.example.demo.controller;

import com.example.demo.model.LoginDetails;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/users")
  @ResponseStatus(HttpStatus.CREATED)
  void createUser(@RequestBody User user) {
    userService.createUser(user);
  }

  @PostMapping("/user-credentials")
  ResponseEntity<User> getUserDetailsFromCredentials(@RequestBody LoginDetails loginDetails) throws JOSEException {
    User user = userService.getUserDetailsFromCredentials(loginDetails.getUserName(),
      loginDetails.getPassword());
    String jwtAsString = userService.generateJWT(user);
    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtAsString).body(user);
  }

}
