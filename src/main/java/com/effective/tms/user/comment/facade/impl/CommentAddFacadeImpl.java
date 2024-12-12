package com.effective.tms.user.comment.facade.impl;

import com.effective.tms.user.comment.facade.CommentAddFacade;
import com.effective.tms.user.comment.mapper.CommentAddRequestToCommentMapper;
import com.effective.tms.user.comment.mapper.CommentToCommentResponseMapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.service.CommentService;
import com.effective.tms.user.comment.web.model.CommentAddRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;
import org.springframework.stereotype.Component;

@Component
public class CommentAddFacadeImpl implements CommentAddFacade {
    private final CommentService commentService;
    private final CommentAddRequestToCommentMapper commentAddRequestToCommentMapper;
    private final CommentToCommentResponseMapper commentToCommentResponseMapper;

    public CommentAddFacadeImpl(CommentService commentService,
                                CommentAddRequestToCommentMapper commentAddRequestToCommentMapper,
                                CommentToCommentResponseMapper commentToCommentResponseMapper) {
        this.commentService = commentService;
        this.commentAddRequestToCommentMapper = commentAddRequestToCommentMapper;
        this.commentToCommentResponseMapper = commentToCommentResponseMapper;
    }

    @Override
    public CommentResponse addComment(Long taskId, CommentAddRequest commentAddRequest) {
        Comment comment = commentAddRequestToCommentMapper.map(commentAddRequest);
        Comment createdComment = commentService.addComment(comment);
        return commentToCommentResponseMapper.map(createdComment);
    }
}
