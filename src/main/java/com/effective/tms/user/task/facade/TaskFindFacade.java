package com.effective.tms.user.task.facade;

import com.effective.tms.user.task.web.model.TaskResponse;

import java.util.Collection;

public interface TaskFindFacade {
    Collection<TaskResponse> findTasks();
}
