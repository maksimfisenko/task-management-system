package com.effective.tms.user.task.facade.impl;

import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.facade.TaskDeleteFacade;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Transactional
public class TaskDeleteFacadeImpl implements TaskDeleteFacade {

    private final IdentityApiService identityApiService;
    private final TaskService taskService;
    private final UserProfileApiService userProfileApiService;

    public TaskDeleteFacadeImpl(IdentityApiService identityApiService,
                                TaskService taskService,
                                UserProfileApiService userProfileApiService) {
        this.identityApiService = identityApiService;
        this.taskService = taskService;
        this.userProfileApiService = userProfileApiService;
    }

    @Override
    public void deleteTask(Long id) {
        CurrentUserApiModel currentUser = identityApiService
                .getCurrentUser()
                .orElseThrow(() -> new RuntimeException("Current user is null"));

        UserRole adminRole = identityApiService.getAdminRole();

        if (!currentUser.authorities().contains(adminRole)) {
            throw new RuntimeException("Current user does not have permission to delete task");
        }
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
