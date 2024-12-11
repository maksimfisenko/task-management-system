package com.effective.tms.user.task.facade.impl;

import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.facade.TaskDeleteFacade;
import com.effective.tms.user.task.facade.TaskEditFacade;
import com.effective.tms.user.task.mapper.TaskEditRequestToTaskMapper;
import com.effective.tms.user.task.mapper.TaskToTaskResponseMapper;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import com.effective.tms.user.task.web.model.TaskEditRequest;
import com.effective.tms.user.task.web.model.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TaskDeleteFacadeImpl implements TaskDeleteFacade {

    private final TaskService taskService;
    private final UserProfileApiService userProfileApiService;

    public TaskDeleteFacadeImpl(TaskService taskService, UserProfileApiService userProfileApiService) {
        this.taskService = taskService;
        this.userProfileApiService = userProfileApiService;
    }

    @Override
    public void deleteTask(Long id) {
        UserProfile actor = userProfileApiService.getCurrentUserProfile();
        UserProfile author = taskService
                .findTaskById(id)
                .map(Task::getAuthor)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!Objects.equals(actor.getId(), author.getId())) {
            throw new RuntimeException("Actor is not author of the task");
        }

        taskService.deleteTask(id);
    }
}
