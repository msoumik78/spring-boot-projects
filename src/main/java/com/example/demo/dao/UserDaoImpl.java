package com.example.demo.dao;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements IUserDao {
  private final JdbcTemplate jdbcTemplate;

  private final String insertSQL = "INSERT INTO users(user_name, password, full_name) VALUES (?,?,?)";
  private final String updateSQL = "Update users set password = ?  where id = ?";
  private final String selectSQL = "Select * from users where user_name = ? and password = ? ";


  @Override
  public void createUser(User user) {
    jdbcTemplate.update(insertSQL,user.getUsername(), user.getPassword(), user.getFullName());
  }

  @Override
  public User getUserDetails(String userName, String password) {
    return jdbcTemplate.queryForObject(selectSQL,
      new Object[]{userName, password},
      (rs, rowNum) ->
        new User(
          rs.getInt("id"),
          rs.getString("user_name"),
          "",
          rs.getString("full_name")
        ));
  }

  @Override
  public void updateUserPassword(int userId, String newPassword) {
    jdbcTemplate.update(updateSQL, newPassword, userId);
  }
}
