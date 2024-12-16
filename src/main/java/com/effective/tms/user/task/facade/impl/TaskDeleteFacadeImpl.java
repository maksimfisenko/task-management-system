package com.effective.tms.user.task.facade.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.user.task.facade.TaskDeleteFacade;
import com.effective.tms.user.task.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import static com.effective.tms.common.constants.FacadeConstants.*;

@Component
@Transactional
public class TaskDeleteFacadeImpl implements TaskDeleteFacade {

    private final IdentityApiService identityApiService;
    private final TaskService taskService;

    public TaskDeleteFacadeImpl(IdentityApiService identityApiService,
                                TaskService taskService) {
        this.identityApiService = identityApiService;
        this.taskService = taskService;
    }

    @Override
    public void deleteTask(Long id) {
        CurrentUserApiModel currentUser = identityApiService
                .getCurrentUser()
                .orElseThrow(() -> new TmsException(CANT_RETRIEVE_CUR_USER));

        UserRole adminRole = identityApiService.getAdminRole();

        if (!currentUser.authorities().contains(adminRole)) {
            throw new TmsException(String.format(CANT_DELETE_TASK, currentUser.userAccountId()));
        }

        taskService.deleteTask(id);
    }
}
