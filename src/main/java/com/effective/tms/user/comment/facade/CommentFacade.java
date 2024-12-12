package com.effective.tms.user.comment.facade;

import com.effective.tms.user.comment.web.model.CommentAddRequest;
import com.effective.tms.user.comment.web.model.CommentEditRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;

import java.util.Collection;

public interface CommentFacade {
    CommentResponse addComment(Long taskId, CommentAddRequest commentAddRequest);
    CommentResponse updateComment(CommentEditRequest commentEditRequest);
    void deleteComment(Long commentId);
    Collection<CommentResponse> getCommentsByTaskId(Long taskId);
}
