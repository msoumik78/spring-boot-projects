package com.example.demo.dao;

import com.example.demo.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskDaoImpl implements ITaskDao {
  private final JdbcTemplate jdbcTemplate;

  private final String insertSQL = "INSERT INTO tasks(task_name, task_description, task_completion_percentage, task_end_date) VALUES (?,?,?, ?)";
  private final String updateSQL = "Update tasks set task_name = ? , task_description = ?, task_completion_percentage = ?, task_end_date = ? where id = ?";
  private final String selectSQL = "Select * from tasks where id = ? ";
  private final String getAllSQL = "Select task_name from tasks";
  private final String deleteSQL = "Delete from tasks where id = ? ";



  @Override
  public void createTask(Task task) {
    jdbcTemplate.update(insertSQL,  task.getTaskName(), task.getTaskDescription(), task.getTaskCompletionPercentage(), task.getTaskEndDate());
  }

  @Override
  public Task getTaskDetails(int taskId) {
    return jdbcTemplate.queryForObject(selectSQL,
      new Object[]{taskId},
      (rs, rowNum) ->
        new Task(
          rs.getInt("id"),
          rs.getString("task_name"),
          rs.getString("task_description"),
          rs.getInt("task_completion_percentage"),
          rs.getDate("task_end_date")
        ));
  }

  @Override
  public void updateTaskDetails(int taskId, Task task) {
    jdbcTemplate.update(updateSQL, task.getTaskName(), task.getTaskDescription(), task.getTaskCompletionPercentage(), task.getTaskEndDate(), taskId);
  }

  @Override
  public List<String> getAllTasks() {
    return jdbcTemplate.query(getAllSQL,
      (rs, rowNum) -> rs.getString("task_name"));
  }

  @Override
  public void deleteTask(int taskId) {
    jdbcTemplate.update(deleteSQL, taskId);
  }
}
