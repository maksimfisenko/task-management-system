package com.effective.tms.user.comment.facade.impl;

import com.effective.tms.user.comment.facade.CommentEditFacade;
import com.effective.tms.user.comment.mapper.CommentEditRequestToCommentMapper;
import com.effective.tms.user.comment.mapper.CommentToCommentResponseMapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.service.CommentService;
import com.effective.tms.user.comment.web.model.CommentEditRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;
import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CommentEditFacadeImpl implements CommentEditFacade {
    private final UserProfileApiService userProfileApiService;
    private final CommentService commentService;
    private final CommentEditRequestToCommentMapper commentEditRequestToCommentMapper;
    private final CommentToCommentResponseMapper commentToCommentResponseMapper;

    public CommentEditFacadeImpl(UserProfileApiService userProfileApiService,
                                 CommentService commentService,
                                 CommentEditRequestToCommentMapper commentEditRequestToCommentMapper,
                                 CommentToCommentResponseMapper commentToCommentResponseMapper) {
        this.userProfileApiService = userProfileApiService;
        this.commentService = commentService;
        this.commentEditRequestToCommentMapper = commentEditRequestToCommentMapper;
        this.commentToCommentResponseMapper = commentToCommentResponseMapper;
    }

    @Override
    public CommentResponse editComment(CommentEditRequest commentEditRequest) {
        UserProfile actor = userProfileApiService.getCurrentUserProfile();
        UserProfile author = commentService
                .findById(commentEditRequest.id())
                .map(Comment::getAuthor)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!Objects.equals(actor.getId(), author.getId())) {
            throw new RuntimeException("Actor is not author of the comment");
        }

        Comment comment = commentEditRequestToCommentMapper.map(commentEditRequest);
        Comment updatedComment = commentService.updateComment(comment);
        return commentToCommentResponseMapper.map(updatedComment);
    }
}
