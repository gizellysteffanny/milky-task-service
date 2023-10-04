package com.milky.milkytaskservice.model.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.milky.milkytaskservice.dto.TaskDTO;
import com.milky.milkytaskservice.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
public class TaskMapperTest {
    @InjectMocks
    private TaskMapper taskMapper;

    @Spy
    ModelMapper modelMapper;

    @Test
    @DisplayName("Should map Task to TaskDTO")
    void shouldMapTaskToTaskDto() {
        Task task = Task.builder()
                .id("1")
                .title("task title")
                .description("task description")
                .completed(false)
                .createdAt(new Date())
                .build();

        assertNotNull(taskMapper.toDTO(task));
    }

    @Test
    @DisplayName("Should map TaskDTO to Task")
    void shouldMapTaskDtoToTask() {
        TaskDTO taskDTO = TaskDTO.builder()
                .title("task title")
                .description("task description")
                .completed(false)
                .build();

        assertNotNull(taskMapper.toEntity(taskDTO));
    }
}
