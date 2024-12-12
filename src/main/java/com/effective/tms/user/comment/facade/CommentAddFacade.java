package com.effective.tms.user.comment.facade;

import com.effective.tms.user.comment.web.model.CommentAddRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;

public interface CommentAddFacade {
    CommentResponse addComment(Long taskId, CommentAddRequest commentAddRequest);
}
