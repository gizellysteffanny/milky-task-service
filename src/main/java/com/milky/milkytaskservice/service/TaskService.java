package com.milky.milkytaskservice.service;

import com.milky.milkytaskservice.model.Task;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.milky.milkytaskservice.dto.TaskDTO;
import com.milky.milkytaskservice.model.mapper.TaskMapper;
import com.milky.milkytaskservice.repository.TaskRepository;

@Validated
@Slf4j
@Service
public class TaskService {
  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;
  
  public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
  }

  public TaskDTO create(@Valid TaskDTO taskDTO, @NotNull @NotBlank String user) {
    log.info("Creating task from user({}): {}", user, taskDTO);
    Task task = taskMapper.toEntity(taskDTO);
    task.setUser(user);

    Task savedTask = taskRepository.save(task);
    log.info("Task created: {}", savedTask);
    return taskMapper.toDTO(savedTask);
  }
}
