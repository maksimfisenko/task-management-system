package com.effective.tms.user.comment.facade.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.user.comment.facade.CommentEditFacade;
import com.effective.tms.user.comment.mapper.CommentEditRequestToCommentMapper;
import com.effective.tms.user.comment.mapper.CommentToCommentResponseMapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.service.CommentService;
import com.effective.tms.user.comment.web.model.CommentEditRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import static com.effective.tms.common.constants.FacadeConstants.*;

@Component
@Transactional
public class CommentEditFacadeImpl implements CommentEditFacade {
    private final IdentityApiService identityApiService;
    private final CommentService commentService;
    private final CommentEditRequestToCommentMapper commentEditRequestToCommentMapper;
    private final CommentToCommentResponseMapper commentToCommentResponseMapper;

    public CommentEditFacadeImpl(IdentityApiService identityApiService,
                                 CommentService commentService,
                                 CommentEditRequestToCommentMapper commentEditRequestToCommentMapper,
                                 CommentToCommentResponseMapper commentToCommentResponseMapper) {
        this.identityApiService = identityApiService;
        this.commentService = commentService;
        this.commentEditRequestToCommentMapper = commentEditRequestToCommentMapper;
        this.commentToCommentResponseMapper = commentToCommentResponseMapper;
    }

    @Override
    public CommentResponse editComment(CommentEditRequest commentEditRequest) {
        CurrentUserApiModel currentUser = identityApiService
                .getCurrentUser()
                .orElseThrow(() -> new TmsException(CANT_RETRIEVE_CUR_USER));

        Comment comment = commentService
                .findById(commentEditRequest.id())
                .orElseThrow(() -> new TmsException(String.format(CANT_FIND_COMMENT, commentEditRequest.id())));
        boolean isCommentAuthor = comment.getAuthor().getId().equals(currentUser.userAccountId());

        if (!isCommentAuthor) {
            throw new TmsException(String.format(
                    CANT_EDIT_COMMENT,
                    currentUser.userAccountId(),
                    commentEditRequest.id()
            ));
        }

        Comment mappedComment = commentEditRequestToCommentMapper.map(commentEditRequest);
        Comment updatedComment = commentService.updateComment(mappedComment);
        return commentToCommentResponseMapper.map(updatedComment);
    }
}
