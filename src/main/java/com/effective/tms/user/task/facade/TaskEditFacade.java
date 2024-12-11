package com.effective.tms.user.task.facade;

import com.effective.tms.user.task.web.model.TaskEditRequest;
import com.effective.tms.user.task.web.model.TaskResponse;

public interface TaskEditFacade {
    TaskResponse editTask(TaskEditRequest taskEditRequest);
}
