package com.effective.tms.user.task.facade.impl;

import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.facade.TaskFindFacade;
import com.effective.tms.user.task.mapper.TaskToTaskResponseMapper;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import com.effective.tms.user.task.web.model.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TaskFindFacadeImpl implements TaskFindFacade {

    private final TaskService taskService;
    private final TaskToTaskResponseMapper taskToTaskResponseMapper;
    private final UserProfileApiService userProfileApiService;

    public TaskFindFacadeImpl(TaskService taskService,
                              TaskToTaskResponseMapper taskToTaskResponseMapper,
                              UserProfileApiService userProfileApiService) {
        this.taskService = taskService;
        this.taskToTaskResponseMapper = taskToTaskResponseMapper;
        this.userProfileApiService = userProfileApiService;
    }


    @Override
    public Collection<TaskResponse> findTasks() {
        UserProfile owner = userProfileApiService.getCurrentUserProfile();
        Collection<Task> allOwnerTasks = taskService.findTasksByAuthor(owner);
        return allOwnerTasks.stream().map(taskToTaskResponseMapper::map).toList();
    }

}
