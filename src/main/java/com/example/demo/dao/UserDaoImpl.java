package com.example.demo.dao;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements IUserDao{
  private final JdbcTemplate jdbcTemplate;

  private final String insertSQL = "INSERT INTO users(user_name, user_password, full_name) VALUES (?,?,?)";
  private final String selectSQL = "Select * from users where user_name = ? ";


  @Override
  public void createUser(User user) {
    jdbcTemplate.update(insertSQL, user.getUserName(), user.getPassword(), user.getFullName());
  }

  @Override
  public User getUserDetails(String userName) {
    return jdbcTemplate.queryForObject(selectSQL,
      new Object[]{userName},
      (rs, rowNum) ->
        new User(
          rs.getInt("user_id"),
          rs.getString("user_name"),
          rs.getString("user_password"),
          rs.getString("full_name")
        ));
  }
}
