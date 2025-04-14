package com.vsb.tasks.service;

import com.vsb.tasks.domin.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(UUID taskListId, Task task);
    Optional<Task> getTask(UUID taskListId, UUID id);
    Task updateTask(UUID taskListId, UUID TaskId, Task task);
    void deleteTask(UUID taskListId,UUID taskId);
}
