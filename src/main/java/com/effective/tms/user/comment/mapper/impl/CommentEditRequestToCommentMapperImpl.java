package com.effective.tms.user.comment.mapper.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.user.comment.mapper.CommentEditRequestToCommentMapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.service.CommentService;
import com.effective.tms.user.comment.web.model.CommentEditRequest;
import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CommentEditRequestToCommentMapperImpl implements CommentEditRequestToCommentMapper {

    private final UserProfileApiService userProfileApiService;
    private final CommentService commentService;

    public CommentEditRequestToCommentMapperImpl(UserProfileApiService userProfileApiService,
                                                 CommentService commentService) {
        this.userProfileApiService = userProfileApiService;
        this.commentService = commentService;
    }

    @Override
    public Comment map(CommentEditRequest commentEditRequest) {
        UserProfile currentUser = userProfileApiService.getCurrentUserProfile();

        Comment comment = commentService
                .findById(commentEditRequest.id())
                .orElseThrow(() -> new TmsException("No comment with such id"));

        if (!Objects.equals(currentUser.getId(), comment.getAuthor().getId())) {
            throw new TmsException("Not right user");
        }

        comment.setMessage(commentEditRequest.message());
        return commentService.updateComment(comment);
    }
}
