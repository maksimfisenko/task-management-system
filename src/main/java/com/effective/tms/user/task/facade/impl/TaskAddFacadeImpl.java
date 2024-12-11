package com.effective.tms.user.task.facade.impl;

import com.effective.tms.user.task.facade.TaskAddFacade;
import com.effective.tms.user.task.mapper.TaskAddRequestToTaskMapper;
import com.effective.tms.user.task.mapper.TaskToTaskResponseMapper;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import com.effective.tms.user.task.web.model.TaskAddRequest;
import com.effective.tms.user.task.web.model.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskAddFacadeImpl implements TaskAddFacade {

    private final TaskService taskService;
    private final TaskAddRequestToTaskMapper taskAddRequestToTaskMapper;
    private final TaskToTaskResponseMapper taskToTaskResponseMapper;

    public TaskAddFacadeImpl(TaskService taskService,
                             TaskAddRequestToTaskMapper taskAddRequestToTaskMapper,
                             TaskToTaskResponseMapper taskToTaskResponseMapper) {
        this.taskService = taskService;
        this.taskAddRequestToTaskMapper = taskAddRequestToTaskMapper;
        this.taskToTaskResponseMapper = taskToTaskResponseMapper;
    }

    @Override
    public TaskResponse createTask(TaskAddRequest taskAddRequest) {
        Task task = taskAddRequestToTaskMapper.map(taskAddRequest);
        Task savedTask = taskService.createTask(task);
        return taskToTaskResponseMapper.map(savedTask);
    }
}
