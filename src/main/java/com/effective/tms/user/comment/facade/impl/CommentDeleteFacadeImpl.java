package com.effective.tms.user.comment.facade.impl;

import com.effective.tms.user.comment.facade.CommentDeleteFacade;
import com.effective.tms.user.comment.service.CommentService;
import org.springframework.stereotype.Component;

@Component
public class CommentDeleteFacadeImpl implements CommentDeleteFacade {
    private final CommentService commentService;

    public CommentDeleteFacadeImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentService.deleteComment(commentId);
    }
}
