package com.effective.tms.user.comment.web.model;

import java.time.Instant;

public record CommentResponse(
        Long id,
        String message,
        Long taskId,
        Long authorId,
        Instant createdTimestamp,
        Instant modifiedTimestamp
) {
}
