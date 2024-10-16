package com.example.demo.service;

import com.example.demo.dao.IUserDao;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.PasswordMismatchException;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final IUserDao iUserDao;

  public void createUser(User user) {
    String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    user.setPassword(hashedPassword);
    iUserDao.createUser(user);
  }

  public User getUserDetailsFromCredentials(String userName, String password) {
    User user;
    try {
       user = iUserDao.getUserDetails(userName);
    } catch (EmptyResultDataAccessException e) {
      throw new InvalidInputException("Invalid userName : "+userName);
    }
    if (BCrypt.checkpw(password, user.getPassword())){
      return user;
    } else {
      throw new PasswordMismatchException("Password does not match for userName : "+userName);
    }
  }

}
