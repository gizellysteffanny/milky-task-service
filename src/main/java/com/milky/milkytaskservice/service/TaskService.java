package com.milky.milkytaskservice.service;

import com.milky.milkytaskservice.exception.RecordNotFoundException;
import com.milky.milkytaskservice.exception.TaskException;
import com.milky.milkytaskservice.model.Task;
import com.milky.milkytaskservice.validator.TaskUserValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.milky.milkytaskservice.dto.TaskDTO;
import com.milky.milkytaskservice.model.mapper.TaskMapper;
import com.milky.milkytaskservice.repository.TaskRepository;

@Validated
@Slf4j
@AllArgsConstructor
@Service
public class TaskService {
  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;
  private final TaskUserValidator taskUserValidator;

  public TaskDTO create(@Valid TaskDTO taskDTO, @NotNull @NotBlank String user) {
    log.info("Creating task from user({}): {}", user, taskDTO);

    taskUserValidator.userCanCreateTask(user);

    Task task = taskMapper.toEntity(taskDTO);
    task.setUser(user);

    Task savedTask = taskRepository.save(task);
    log.info("Task created: {}", savedTask);
    return taskMapper.toDTO(savedTask);
  }

  public TaskDTO update(@Valid TaskDTO taskDTO, @NotNull @NotBlank String id, @NotNull @NotBlank String user) {
    log.info("Updating task from user({}): {}", user, taskDTO);
    Task task = taskRepository.findById(id).orElse(null);

    if (task == null) {
      throw new RecordNotFoundException("Task not found");
    }

    taskUserValidator.userCanUpdateTask(user, task.getUser());

    Task savedTask = taskRepository.save(taskMapper.toEntity(taskDTO));
    log.info("Task updated: {}", savedTask);
    return taskMapper.toDTO(savedTask);
  }

}
