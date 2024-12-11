package com.effective.tms.user.task.facade;

import com.effective.tms.user.task.web.model.TaskAddRequest;
import com.effective.tms.user.task.web.model.TaskResponse;

public interface TaskAddFacade {
    TaskResponse createTask(TaskAddRequest taskAddRequest);
}
