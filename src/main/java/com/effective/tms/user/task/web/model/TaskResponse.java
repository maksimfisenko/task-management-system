package com.effective.tms.user.task.web.model;

import com.effective.tms.user.task.model.TaskPriority;
import com.effective.tms.user.task.model.TaskStatus;

import java.time.Instant;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        Long authorId,
        Long executorId,
        Instant createdTimestamp,
        Instant modifiedTimestamp
) {
}
