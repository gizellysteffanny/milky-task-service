package com.milky.milkytaskservice.service;

import com.milky.milkytaskservice.exception.RecordNotFoundException;
import com.milky.milkytaskservice.exception.TaskException;
import com.milky.milkytaskservice.validator.TaskUserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Date;

import com.milky.milkytaskservice.dto.TaskDTO;
import com.milky.milkytaskservice.model.Task;
import com.milky.milkytaskservice.model.mapper.TaskMapper;
import com.milky.milkytaskservice.repository.TaskRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TaskServiceTest {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private TaskUserValidator taskUserValidator;

    @Test
    @DisplayName("should be able to create a task")
    public void createTask() {
        String user = "1";
        TaskDTO taskDTO = TaskDTO.builder()
                .title("task title")
                .description("task description")
                .completed(false)
                .build();

        Task task = Task.builder()
                .title("task title")
                .description("task description")
                .completed(false)
                .createdAt(new Date())
                .build();

        when(taskRepository.save(any())).thenReturn(task);
        when(taskMapper.toEntity(any())).thenReturn(task);
        when(taskMapper.toDTO(any())).thenReturn(taskDTO);
        TaskDTO createdTask = taskService.create(taskDTO, user);

        Assertions.assertEquals(taskDTO.getTitle(), createdTask.getTitle());
    }

    @Test
    @DisplayName("should not be able to create a task without a user")
    public void createTaskWithoutUser() {
        String user = "";
        TaskDTO taskDTO = TaskDTO.builder()
                .title("task title")
                .description("task description")
                .completed(false)
                .build();

        doThrow(new TaskException("User not allowed to create task"))
                .when(taskUserValidator).userCanCreateTask(any());

        assertThrows(TaskException.class, () -> {
            taskService.create(taskDTO, user);
        });
    }

    @Test
    @DisplayName("should be able to update a task")
    public void updateTask() {
        String user = "1";
        String id = "1";
        TaskDTO taskDTO = TaskDTO.builder()
                .id(id)
                .title("task title")
                .description("task description")
                .completed(false)
                .build();

        Task task = Task.builder()
                .title("task title")
                .description("task description")
                .completed(false)
                .createdAt(new Date())
                .user(user)
                .build();

        when(taskRepository.findById(any())).thenReturn(java.util.Optional.of(task));
        when(taskRepository.save(any())).thenReturn(task);
        when(taskMapper.toDTO(any())).thenReturn(taskDTO);
        TaskDTO updatedTask = taskService.update(taskDTO, id, user);

        Assertions.assertEquals(taskDTO.getTitle(), updatedTask.getTitle());
    }

    @Test
    @DisplayName("should not be able to update a task from another user")
    public void updateTaskFromAnotherUser() {
        String user = "1";
        String id = "1";
        TaskDTO taskDTO = TaskDTO.builder()
                .id(id)
                .title("task title")
                .description("task description")
                .completed(false)
                .build();

        Task task = Task.builder()
                .title("task title")
                .description("task description")
                .completed(false)
                .createdAt(new Date())
                .user("2")
                .build();

        when(taskRepository.findById(any())).thenReturn(java.util.Optional.of(task));
        doThrow(new TaskException("User not allowed to update this task"))
                .when(taskUserValidator).userCanUpdateTask(any(), any());

        assertThrows(TaskException.class, () -> {
            taskService.update(taskDTO, id, user);
        });
    }

    @Test
    @DisplayName("should not be able to update a task that does not exist")
    public void updateTaskThatDoesNotExist() {
        String user = "1";
        String id = "1";
        TaskDTO taskDTO = TaskDTO.builder()
                .id(id)
                .title("task title")
                .description("task description")
                .completed(false)
                .build();

        when(taskRepository.findById(any())).thenReturn(java.util.Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> {
            taskService.update(taskDTO, id, user);
        });
    }
}
