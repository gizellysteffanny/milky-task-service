package com.milky.milkytaskservice.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.milky.milkytaskservice.dto.TaskDTO;
import com.milky.milkytaskservice.model.mapper.TaskMapper;
import com.milky.milkytaskservice.repository.TaskRepository;

@Service
@Validated
public class TaskService {
  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;
  
  public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
  }

  public TaskDTO create(@Valid TaskDTO task) {
    return taskMapper.toDTO(taskRepository.save(taskMapper.toEntity(task)));
  }
}
