package com.example.demo.controller;

import com.example.demo.model.LoginDetails;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
  User getUserDetailsFromCredentials(@RequestBody LoginDetails loginDetails) {
    return userService.getUserDetailsFromCredentials(loginDetails.getUserName(), loginDetails.getPassword());
  }

}
