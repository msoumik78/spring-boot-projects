package com.example.demo.dao;

import com.example.demo.model.Task;

import java.util.List;

public interface ITaskDao {
  void createTask(Task task);
  Task getTaskDetails(int taskId);
  void updateTaskDetails(int taskId, Task task);
  List<String> getAllTasks();

  void deleteTask(int taskId);
}
