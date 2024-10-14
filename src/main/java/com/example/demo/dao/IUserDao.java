package com.example.demo.dao;

import com.example.demo.model.User;

public interface IUserDao {
  void createUser(User user);
  User getUserDetails(String userName, String password);
  void updateUserPassword(int userId, String newPassword);
}
