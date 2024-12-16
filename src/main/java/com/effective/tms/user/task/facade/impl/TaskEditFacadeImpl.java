package com.effective.tms.user.task.facade.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.facade.TaskEditFacade;
import com.effective.tms.user.task.mapper.TaskEditRequestToTaskMapper;
import com.effective.tms.user.task.mapper.TaskToTaskResponseMapper;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import com.effective.tms.user.task.web.model.TaskEditRequest;
import com.effective.tms.user.task.web.model.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.effective.tms.common.constants.FacadeConstants.CANT_EDIT_TASK;
import static com.effective.tms.common.constants.FacadeConstants.CANT_FIND_TASK;

@Component
public class TaskEditFacadeImpl implements TaskEditFacade {

    private final TaskService taskService;
    private final TaskEditRequestToTaskMapper taskEditRequestToTaskMapper;
    private final TaskToTaskResponseMapper taskToTaskResponseMapper;
    private final UserProfileApiService userProfileApiService;

    public TaskEditFacadeImpl(TaskService taskService,
                              TaskEditRequestToTaskMapper taskEditRequestToTaskMapper,
                              TaskToTaskResponseMapper taskToTaskResponseMapper,
                              UserProfileApiService userProfileApiService) {
        this.taskService = taskService;
        this.taskEditRequestToTaskMapper = taskEditRequestToTaskMapper;
        this.taskToTaskResponseMapper = taskToTaskResponseMapper;
        this.userProfileApiService = userProfileApiService;
    }

    @Override
    public TaskResponse editTask(TaskEditRequest taskEditRequest) {
        UserProfile actor = userProfileApiService.getCurrentUserProfile();
        UserProfile author = taskService
                .findTaskById(taskEditRequest.id())
                .map(Task::getAuthor)
                .orElseThrow(() -> new TmsException(String.format(CANT_FIND_TASK, taskEditRequest.id())));

        if (!Objects.equals(actor.getId(), author.getId())) {
            throw new TmsException(String.format(CANT_EDIT_TASK, actor.getId(), taskEditRequest.id()));
        }

        Task task = taskEditRequestToTaskMapper.map(taskEditRequest);
        Task updatedtask = taskService.updateTask(task);

        return taskToTaskResponseMapper.map(updatedtask);
    }
}
