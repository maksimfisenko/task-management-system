package com.effective.tms.user.task.facade.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.facade.TaskFindFacade;
import com.effective.tms.user.task.mapper.TaskToTaskResponseMapper;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import com.effective.tms.user.task.web.model.TaskResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static com.effective.tms.common.constants.FacadeConstants.CANT_RETRIEVE_CUR_USER;

@Component
@Transactional
public class TaskFindFacadeImpl implements TaskFindFacade {

    private final IdentityApiService identityApiService;
    private final TaskService taskService;
    private final TaskToTaskResponseMapper taskToTaskResponseMapper;
    private final UserProfileApiService userProfileApiService;

    public TaskFindFacadeImpl(IdentityApiService identityApiService, TaskService taskService,
                              TaskToTaskResponseMapper taskToTaskResponseMapper,
                              UserProfileApiService userProfileApiService) {
        this.identityApiService = identityApiService;
        this.taskService = taskService;
        this.taskToTaskResponseMapper = taskToTaskResponseMapper;
        this.userProfileApiService = userProfileApiService;
    }


    @Override
    public Collection<TaskResponse> findTasks() {
        CurrentUserApiModel currentUser = identityApiService
                .getCurrentUser()
                .orElseThrow(() -> new TmsException(CANT_RETRIEVE_CUR_USER));
        UserProfile userProfile = userProfileApiService.getCurrentUserProfile();
        UserRole adminRole = identityApiService.getAdminRole();
        boolean isAdmin = currentUser.authorities().contains(adminRole);
        if (isAdmin) {
            Collection<Task> allAuthorTasks = taskService.findTasksByAuthor(userProfile);
            return allAuthorTasks.stream().map(taskToTaskResponseMapper::map).toList();
        }
        Collection<Task> allExecutorTasks = taskService.findTasksByExecutor(userProfile);
        return allExecutorTasks.stream().map(taskToTaskResponseMapper::map).toList();
    }

}
