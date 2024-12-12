package com.effective.tms.user.comment.facade;

import com.effective.tms.user.comment.web.model.CommentResponse;

import java.util.Collection;

public interface CommentFindFacade {
    Collection<CommentResponse> findCommentsByTaskId(Long taskId);
}
