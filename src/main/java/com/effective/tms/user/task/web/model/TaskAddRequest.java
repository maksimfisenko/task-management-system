package com.effective.tms.user.task.web.model;

import com.effective.tms.user.task.model.TaskPriority;
import com.effective.tms.user.task.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TaskAddRequest(
        @NotBlank
        @Size(max = 32)
        String title,
        @Size(max = 256)
        String description,
//        @Pattern(regexp = "WAITING|IN_PROGRESS|COMPLETED")
        TaskStatus status,
//        @Pattern(regexp = "LOW|AVERAGE|HIGH")
        TaskPriority priority,
        Long executorId
) {
}
