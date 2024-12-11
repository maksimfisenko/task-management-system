package com.effective.tms.user.task.mapper.impl;

import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.mapper.TaskEditRequestToTaskMapper;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import com.effective.tms.user.task.web.model.TaskEditRequest;
import org.springframework.stereotype.Component;

@Component
public class TaskEditRequestToTaskMapperImpl implements TaskEditRequestToTaskMapper {

    private final TaskService taskService;
    private final UserProfileApiService userProfileApiService;

    public TaskEditRequestToTaskMapperImpl(TaskService taskService, UserProfileApiService userProfileApiService) {
        this.taskService = taskService;
        this.userProfileApiService = userProfileApiService;
    }

    @Override
    public Task map(TaskEditRequest taskEditRequest) {
        Task currentTask = taskService
                .findTaskById(taskEditRequest.id())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        currentTask.setTitle(taskEditRequest.title());
        currentTask.setDescription(taskEditRequest.description());
        currentTask.setStatus(taskEditRequest.status());
        currentTask.setPriority(taskEditRequest.priority());

        UserProfile newExecutor = userProfileApiService
                .getUserProfileById(taskEditRequest.id());

        currentTask.setExecutor(newExecutor);

        return currentTask;
    }
}
