package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
  private int taskId;
  private String taskName;
  private String taskDescription;
  private int taskCompletionPercentage;
  private Date taskEndDate;
}
