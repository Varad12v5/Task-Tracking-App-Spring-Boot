package com.vsb.tasks.mappers;

import com.vsb.tasks.domin.dto.TaskListDto;
import com.vsb.tasks.domin.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);


}
