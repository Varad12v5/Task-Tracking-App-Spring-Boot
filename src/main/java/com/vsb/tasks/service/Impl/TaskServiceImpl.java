package com.vsb.tasks.service.Impl;

import com.vsb.tasks.domin.entities.Task;
import com.vsb.tasks.domin.entities.TaskList;
import com.vsb.tasks.domin.entities.TaskPriority;
import com.vsb.tasks.domin.entities.TaskStatus;
import com.vsb.tasks.repo.TaskListRepo;
import com.vsb.tasks.repo.TaskRepo;
import com.vsb.tasks.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final TaskListRepo taskListRepo;
    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepo.findByTaskListId(taskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (null != task.getId())
        {
            throw new IllegalArgumentException("Task already exists with an ID");
        }
        if (null == task.getTitle() || task.getTitle().isBlank())
        {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus=TaskStatus.OPEN;

        TaskList taskList = taskListRepo.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("TaskList does not exist"));

        LocalDateTime dateTime=LocalDateTime.now();
        Task task1=new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                dateTime,
                dateTime
        );

        return taskRepo.save(task1);

    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID id) {
        return taskRepo.findByTaskListIdAndId(taskListId,id);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID TaskId, Task task) {
        if (null ==task.getId())
        {
            throw new IllegalArgumentException("Task ID cannot be null");
        }
        if (!Objects.equals(TaskId,task.getId()))
        {
            throw new IllegalArgumentException("Task IDS do not match");
        }
        if (null ==task.getPriority())
        {
            throw new IllegalArgumentException("Task priority cannot be null");
        }
        if (null ==task.getStatus())
        {
            throw new IllegalArgumentException("Task status cannot be null");
        }

        Task ExistingTask = taskRepo.findByTaskListIdAndId(taskListId, TaskId).orElseThrow(() -> new IllegalArgumentException("Task does not exist"));

        ExistingTask.setTitle(task.getTitle());
        ExistingTask.setDescription(task.getDescription());
        ExistingTask.setDueDate(task.getDueDate());
        ExistingTask.setStatus(task.getStatus());
        ExistingTask.setPriority(task.getPriority());
        ExistingTask.setUpdated(LocalDateTime.now());
        return taskRepo.save(ExistingTask);

    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepo.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
