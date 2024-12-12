package com.effective.tms.user.comment.facade;

import com.effective.tms.user.comment.web.model.CommentEditRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;

public interface CommentEditFacade {
    CommentResponse editComment(CommentEditRequest commentEditRequest);
}
