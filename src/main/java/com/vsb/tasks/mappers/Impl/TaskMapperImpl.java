package com.vsb.tasks.mappers.Impl;

import com.vsb.tasks.domin.dto.TaskDto;
import com.vsb.tasks.domin.entities.Task;
import com.vsb.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;


@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public TaskDto toDto(Task task) {
           return new TaskDto(
                   task.getId(),
                   task.getTitle(),
                   task.getDescription(),
                   task.getDueDate(),
                   task.getPriority(),
                   task.getStatus()
           );
    }

    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,  // taskList - since it's a ManyToOne relationship, it should be set separately
                null,
                null// updated
        );
    }
}
