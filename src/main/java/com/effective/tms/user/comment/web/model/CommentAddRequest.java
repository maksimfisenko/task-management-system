package com.effective.tms.user.comment.web.model;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CommentAddRequest(
        @NotBlank @Length(max = 256) String message,
        Long taskId
) {
}
