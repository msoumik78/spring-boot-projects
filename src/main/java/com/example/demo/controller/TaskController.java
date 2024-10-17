package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/1")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @PostMapping("/tasks")
  @ResponseStatus(HttpStatus.CREATED)
  void createTask(@RequestBody Task task) {
    System.out.println("In task controller...createTask method");
    taskService.createTask(task);
  }

  @PutMapping("/tasks/{taskId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void updateTask(@PathVariable("taskId") final int taskId, @RequestBody Task task) {
    taskService.updateTask(taskId,task);
  }

  @GetMapping("/users/{userId}/tasks")
  List<String> getAllTasksForUser(@PathVariable("userId") final int userId) {
    return taskService.getAllTasks(userId);
  }

  @GetMapping("/tasks/{taskId}")
  Task getTaskDetail(@PathVariable("taskId") final int taskId) {
    return taskService.getTaskDetails(taskId);
  }

  @DeleteMapping("/tasks/{taskId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteTask(@PathVariable("taskId") final int taskId) {
    taskService.deleteTask(taskId);
  }

}
