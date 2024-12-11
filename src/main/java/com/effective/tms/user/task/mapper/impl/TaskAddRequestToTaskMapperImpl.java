package com.effective.tms.user.task.mapper.impl;

import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.task.mapper.TaskAddRequestToTaskMapper;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.web.model.TaskAddRequest;
import org.springframework.stereotype.Component;

@Component
public class TaskAddRequestToTaskMapperImpl implements TaskAddRequestToTaskMapper {

    private final UserProfileApiService userProfileApiService;

    public TaskAddRequestToTaskMapperImpl(UserProfileApiService userProfileApiService) {
        this.userProfileApiService = userProfileApiService;
    }

    @Override
    public Task map(TaskAddRequest taskAddRequest) {
        Task task = new Task();
        task.setTitle(taskAddRequest.title());
        task.setDescription(taskAddRequest.description());
        task.setStatus(taskAddRequest.status());
        task.setPriority(taskAddRequest.priority());
        task.setAuthor(userProfileApiService.getCurrentUserProfile());
        task.setExecutor(userProfileApiService.getUserProfileById(taskAddRequest.executorId()));
        return task;
    }
}
