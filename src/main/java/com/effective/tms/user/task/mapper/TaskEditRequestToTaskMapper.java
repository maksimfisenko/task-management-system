package com.effective.tms.user.task.mapper;

import com.effective.tms.security.mapper.Mapper;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.web.model.TaskAddRequest;
import com.effective.tms.user.task.web.model.TaskEditRequest;

public interface TaskEditRequestToTaskMapper extends Mapper<Task, TaskEditRequest> {
}
