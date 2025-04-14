package com.vsb.tasks.mappers;

import com.vsb.tasks.domin.dto.TaskDto;
import com.vsb.tasks.domin.entities.Task;

public interface TaskMapper {
    TaskDto toDto(Task task);

    Task fromDto(TaskDto taskDto);
}
