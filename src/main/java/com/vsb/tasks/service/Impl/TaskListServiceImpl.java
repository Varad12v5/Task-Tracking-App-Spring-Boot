package com.vsb.tasks.service.Impl;

import com.vsb.tasks.domin.entities.TaskList;
import com.vsb.tasks.repo.TaskListRepo;
import com.vsb.tasks.service.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepo taskListRepo;

    public TaskListServiceImpl(TaskListRepo taskListRepo) {
        this.taskListRepo = taskListRepo;
    }
    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepo.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null !=taskList.getId())
        {
            throw new IllegalArgumentException("TaskList already exists with an ID");
        }
        if (null==taskList.getTitle() || taskList.getTitle().isBlank())
        {
            throw new IllegalArgumentException("TaskList title cannot be empty");
        }
        LocalDateTime dateTime=LocalDateTime.now();
        return taskListRepo.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                dateTime,
                dateTime
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepo.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (null == taskList.getId())
        {
            throw new IllegalArgumentException("TaskList ID cannot be null");
        }
        if (!Objects.equals(taskListId,taskList.getId()))
        {
            throw new IllegalArgumentException("Attempting to change TaskList ID is not allowed");
        }

        TaskList existingTaskList = taskListRepo.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("TaskList does not exist"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepo.save(existingTaskList);

    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepo.deleteById(taskListId);
    }
}
