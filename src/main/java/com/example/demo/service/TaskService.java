package com.example.demo.service;

import com.example.demo.dao.ITaskDao;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
  private final ITaskDao iTaskDao;

  public void updateTask(int taskId, Task task) {
    getTaskDetails(taskId);
    iTaskDao.updateTaskDetails(taskId,task);
  }
  public void deleteTask(int taskId) {
    getTaskDetails(taskId);
    iTaskDao.deleteTask(taskId);
  }
  public void createTask(Task task) {
    if (task.getTaskDescription() == null) {
      task.setTaskDescription(task.getTaskName());
    }
    if (task.getTaskEndDate() == null) {
      task.setTaskEndDate(Date.from((LocalDate.now().plusDays(7)).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
    iTaskDao.createTask(task);
  }
  public List<String> getAllTasks(int userId) {
    return iTaskDao.getAllTasks(userId);
  }
  public Task getTaskDetails(int taskId) {
    try {
      return iTaskDao.getTaskDetails(taskId);
    } catch (EmptyResultDataAccessException e) {
      throw new InvalidInputException("Task id :"+taskId+" does not exist!");
    }
  }

}
