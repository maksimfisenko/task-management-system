package com.effective.tms.user.task.mapper.impl;

import com.effective.tms.user.task.mapper.TaskToTaskResponseMapper;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.web.model.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskToTaskResponseMapperImpl implements TaskToTaskResponseMapper {
    @Override
    public TaskResponse map(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getAuthor().getId(),
                task.getExecutor().getId(),
                task.getCreatedTimestamp(),
                task.getModifiedTimestamp()
        );
    }
}
