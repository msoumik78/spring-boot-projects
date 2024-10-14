package com.example.demo.service;

import com.example.demo.dao.IUserDao;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final IUserDao iUserDao;

  public boolean verifyCredentials(String userName, String password) {
     iUserDao.getUserDetails(userName, password);
     return false;
  }

  public void updateUserPassword(int userId, String password) {
    iUserDao.updateUserPassword(userId, password);
  }

  public void createUser(User user) {
    iUserDao.createUser(user);
  }

}
