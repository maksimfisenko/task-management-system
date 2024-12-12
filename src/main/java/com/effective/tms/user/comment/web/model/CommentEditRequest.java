package com.effective.tms.user.comment.web.model;

public record CommentEditRequest(
        Long id,
        String message
) {
}
