package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1")
@RequiredArgsConstructor
public class UserController {

  private UserService userService;

  @PostMapping("/users")
  void createUser(User user) {
    userService.createUser(user);
  }

  @PutMapping("/users")
  void updateUser(User user) {
    userService.createUser(user);
  }

  @GetMapping("/user-password")
  void validateUser(User user) {
    userService.createUser(user);
  }

}
