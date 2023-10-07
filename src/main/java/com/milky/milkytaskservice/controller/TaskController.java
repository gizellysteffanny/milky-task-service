package com.milky.milkytaskservice.controller;

import com.milky.milkytaskservice.dto.TaskDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.milky.milkytaskservice.service.TaskService;

import java.net.URI;

@Validated
@Slf4j
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create(@RequestBody @Valid TaskDTO task, @RequestHeader(required = true, name = "user") @NotNull @NotBlank String user) {
        log.info("POST /api/v1/tasks -> create task from user({})", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(task, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable("id") String id, @RequestBody @Valid TaskDTO task, @RequestHeader(required = true, name = "user") @NotNull @NotBlank String user) {
        log.info("PUT /api/v1/tasks/{} -> update task from user({})", id, user);
        return ResponseEntity.ok(taskService.update(task, id, user));
    }
}
