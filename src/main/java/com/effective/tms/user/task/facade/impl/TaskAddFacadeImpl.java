package com.effective.tms.user.task.facade.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.user.task.facade.TaskAddFacade;
import com.effective.tms.user.task.mapper.TaskAddRequestToTaskMapper;
import com.effective.tms.user.task.mapper.TaskToTaskResponseMapper;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import com.effective.tms.user.task.web.model.TaskAddRequest;
import com.effective.tms.user.task.web.model.TaskResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import static com.effective.tms.common.constants.FacadeConstants.*;

@Component
@Transactional
public class TaskAddFacadeImpl implements TaskAddFacade {
    private final IdentityApiService identityApiService;
    private final TaskService taskService;
    private final TaskAddRequestToTaskMapper taskAddRequestToTaskMapper;
    private final TaskToTaskResponseMapper taskToTaskResponseMapper;

    public TaskAddFacadeImpl(IdentityApiService identityApiService,
                             TaskService taskService,
                             TaskAddRequestToTaskMapper taskAddRequestToTaskMapper,
                             TaskToTaskResponseMapper taskToTaskResponseMapper) {
        this.identityApiService = identityApiService;
        this.taskService = taskService;
        this.taskAddRequestToTaskMapper = taskAddRequestToTaskMapper;
        this.taskToTaskResponseMapper = taskToTaskResponseMapper;
    }

    @Override
    public TaskResponse createTask(TaskAddRequest taskAddRequest) {
        CurrentUserApiModel currentUser = identityApiService
                .getCurrentUser()
                .orElseThrow(() -> new TmsException(CANT_RETRIEVE_CUR_USER));

        UserRole adminRole = identityApiService.getAdminRole();

        if (!currentUser.authorities().contains(adminRole)) {
            throw new TmsException(String.format(CANT_ADD_TASK, currentUser.userAccountId()));
        }

        Task task = taskAddRequestToTaskMapper.map(taskAddRequest);
        Task savedTask = taskService.createTask(task);
        return taskToTaskResponseMapper.map(savedTask);
    }
}
