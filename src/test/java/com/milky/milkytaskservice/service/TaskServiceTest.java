package com.milky.milkytaskservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
        when(taskMapper.toDTO(any())).thenReturn(taskDTO);
        TaskDTO createdTask = taskService.create(taskDTO, user);

        Assertions.assertEquals(taskDTO.getTitle(), createdTask.getTitle());
    }
}
