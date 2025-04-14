package com.vsb.tasks.domin.dto;

import com.vsb.tasks.domin.entities.TaskPriority;
import com.vsb.tasks.domin.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDateTime dueDate,
    TaskPriority priority,
    TaskStatus status

) {
}
