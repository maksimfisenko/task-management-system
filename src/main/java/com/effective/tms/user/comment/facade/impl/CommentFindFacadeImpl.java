package com.effective.tms.user.comment.facade.impl;

import com.effective.tms.common.exception.TmsException;
import com.effective.tms.security.api.model.CurrentUserApiModel;
import com.effective.tms.security.api.service.IdentityApiService;
import com.effective.tms.security.model.UserRole;
import com.effective.tms.user.comment.facade.CommentFindFacade;
import com.effective.tms.user.comment.mapper.CommentToCommentResponseMapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.service.CommentService;
import com.effective.tms.user.comment.web.model.CommentResponse;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static com.effective.tms.common.constants.FacadeConstants.*;

@Component
@Transactional
public class CommentFindFacadeImpl implements CommentFindFacade {
    private final IdentityApiService identityApiService;
    private final TaskService taskService;
    private final CommentService commentService;
    private final CommentToCommentResponseMapper commentToCommentResponseMapper;

    public CommentFindFacadeImpl(IdentityApiService identityApiService,
                                 TaskService taskService,
                                 CommentService commentService,
                                 CommentToCommentResponseMapper commentToCommentResponseMapper) {
        this.identityApiService = identityApiService;
        this.taskService = taskService;
        this.commentService = commentService;
        this.commentToCommentResponseMapper = commentToCommentResponseMapper;
    }

    @Override
    public Collection<CommentResponse> findCommentsByTaskId(Long taskId) {
        CurrentUserApiModel currentUser = identityApiService
                .getCurrentUser()
                .orElseThrow(() -> new TmsException(CANT_RETRIEVE_CUR_USER));
        UserRole adminRole = identityApiService.getAdminRole();
        boolean isAdmin = currentUser.authorities().contains(adminRole);

        Task task = taskService
                .findTaskById(taskId)
                .orElseThrow(() -> new TmsException(String.format(CANT_FIND_TASK, taskId)));
        boolean isTaskExecutor = task.getExecutor().getId().equals(currentUser.userAccountId());

        if (!isAdmin && !isTaskExecutor) {
            throw new TmsException(String.format(CANT_SEE_COMMENTS, currentUser.userAccountId(), taskId));
        }

        Collection<Comment> comments = commentService.findCommentsByTask(task);
        return comments.stream().map(commentToCommentResponseMapper::map).toList();
    }
}
