package com.effective.tms.user.task.web.model;

import com.effective.tms.user.task.model.TaskPriority;
import com.effective.tms.user.task.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskEditRequest(
        Long id,
        @NotBlank
        @Size(max = 50)
        String title,
        @Size(max = 200)
        String description,
        TaskStatus status,
        TaskPriority priority,
        Long executorId
) {
}
