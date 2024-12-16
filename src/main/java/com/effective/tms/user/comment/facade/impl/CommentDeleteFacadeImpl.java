package com.effective.tms.user.comment.facade.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.user.comment.facade.CommentDeleteFacade;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import static com.effective.tms.common.constants.FacadeConstants.*;

@Component
@Transactional
public class CommentDeleteFacadeImpl implements CommentDeleteFacade {
    private final IdentityApiService identityApiService;
    private final CommentService commentService;

    public CommentDeleteFacadeImpl(IdentityApiService identityApiService,
                                   CommentService commentService) {
        this.identityApiService = identityApiService;
        this.commentService = commentService;
    }

    @Override
    public void deleteComment(Long commentId) {
        CurrentUserApiModel currentUser = identityApiService
                .getCurrentUser()
                .orElseThrow(() -> new TmsException(CANT_RETRIEVE_CUR_USER));
        UserRole adminRole = identityApiService.getAdminRole();
        boolean isAdmin = currentUser.authorities().contains(adminRole);

        Comment comment = commentService
                .findById(commentId)
                .orElseThrow(() -> new TmsException(String.format(CANT_FIND_COMMENT, commentId)));
        boolean isCommentAuthor = comment.getAuthor().getId().equals(currentUser.userAccountId());


        if (!isAdmin && !isCommentAuthor) {
            throw new TmsException(String.format(CANT_DELETE_COMMENT, currentUser.userAccountId(), commentId));
        }
        commentService.deleteComment(commentId);
    }
}
