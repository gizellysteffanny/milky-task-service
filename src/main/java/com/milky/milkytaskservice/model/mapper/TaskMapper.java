package com.milky.milkytaskservice.model.mapper;

import com.milky.milkytaskservice.dto.TaskDTO;
import com.milky.milkytaskservice.model.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private final ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TaskDTO toDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    public Task toEntity(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}
