package com.effective.tms.user.comment.facade.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.user.comment.facade.CommentAddFacade;
import com.effective.tms.user.comment.mapper.CommentAddRequestToCommentMapper;
import com.effective.tms.user.comment.mapper.CommentToCommentResponseMapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.service.CommentService;
import com.effective.tms.user.comment.web.model.CommentAddRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import static com.effective.tms.common.constants.FacadeConstants.*;

@Component
@Transactional
public class CommentAddFacadeImpl implements CommentAddFacade {

    private final IdentityApiService identityApiService;
    private final TaskService taskService;
    private final CommentService commentService;
    private final CommentAddRequestToCommentMapper commentAddRequestToCommentMapper;
    private final CommentToCommentResponseMapper commentToCommentResponseMapper;

    public CommentAddFacadeImpl(IdentityApiService identityApiService,
                                TaskService taskService,
                                CommentService commentService,
                                CommentAddRequestToCommentMapper commentAddRequestToCommentMapper,
                                CommentToCommentResponseMapper commentToCommentResponseMapper) {
        this.identityApiService = identityApiService;
        this.taskService = taskService;
        this.commentService = commentService;
        this.commentAddRequestToCommentMapper = commentAddRequestToCommentMapper;
        this.commentToCommentResponseMapper = commentToCommentResponseMapper;
    }

    @Override
    public CommentResponse addComment(Long taskId, CommentAddRequest commentAddRequest) {

        CurrentUserApiModel currentUser = identityApiService
                .getCurrentUser()
                .orElseThrow(() -> new TmsException(CANT_RETRIEVE_CUR_USER));
        UserRole adminRole = identityApiService.getAdminRole();
        boolean isAdmin = currentUser.authorities().contains(adminRole);

        Task task = taskService
                .findTaskById(commentAddRequest.taskId())
                .orElseThrow(() -> new TmsException(String.format(CANT_FIND_TASK, commentAddRequest.taskId())));
        boolean isTaskExecutor = task.getExecutor().getId().equals(currentUser.userAccountId());

        if (!isAdmin && !isTaskExecutor) {
            throw new TmsException(String.format(
                    CANT_ADD_COMMENT,
                    currentUser.userAccountId(),
                    commentAddRequest.taskId()
            ));
        }

        Comment comment = commentAddRequestToCommentMapper.map(commentAddRequest);
        Comment createdComment = commentService.addComment(comment);
        return commentToCommentResponseMapper.map(createdComment);
    }
}
