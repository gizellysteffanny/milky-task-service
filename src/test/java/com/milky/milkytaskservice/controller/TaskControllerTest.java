package com.milky.milkytaskservice.controller;

import com.milky.milkytaskservice.dto.TaskDTO;
import com.milky.milkytaskservice.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.when;

public class TaskControllerTest {

    @InjectMocks
    TaskController taskController;

    @Mock
    TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should be able to create a task")
    public void createTask() {
        String user = "1";
        TaskDTO taskDTO = TaskDTO.builder()
                .title("task title")
                .description("task description")
                .completed(false)
                .build();

        when(taskService.create(taskDTO, user)).thenReturn(taskDTO);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<TaskDTO> createdTask = taskController.create(taskDTO, user);

        Assertions.assertEquals(HttpStatus.CREATED, createdTask.getStatusCode());
    }
}
