package com.effective.tms.user.comment.facade.impl;

import com.effective.tms.user.comment.facade.CommentFacade;
import com.effective.tms.user.comment.mapper.CommentAddRequestToCommentMapper;
import com.effective.tms.user.comment.mapper.CommentEditRequestToCommentMapper;
import com.effective.tms.user.comment.mapper.CommentToCommentResponseMapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.service.CommentService;
import com.effective.tms.user.comment.web.model.CommentAddRequest;
import com.effective.tms.user.comment.web.model.CommentEditRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;
import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
public class CommentFacadeImpl implements CommentFacade {

    private final TaskService taskService;
    private final CommentService commentService;
    private final CommentAddRequestToCommentMapper commentAddRequestToCommentMapper;
    private final CommentToCommentResponseMapper commentToCommentResponseMapper;
    private final CommentEditRequestToCommentMapper commentEditRequestToCommentMapper;
    private final UserProfileApiService userProfileApiService;

    public CommentFacadeImpl(TaskService taskService, CommentService commentService,
                             CommentAddRequestToCommentMapper commentAddRequestToCommentMapper,
                             CommentToCommentResponseMapper commentToCommentResponseMapper,
                             CommentEditRequestToCommentMapper commentEditRequestToCommentMapper,
                             UserProfileApiService userProfileApiService) {
        this.taskService = taskService;
        this.commentService = commentService;
        this.commentAddRequestToCommentMapper = commentAddRequestToCommentMapper;
        this.commentToCommentResponseMapper = commentToCommentResponseMapper;
        this.commentEditRequestToCommentMapper = commentEditRequestToCommentMapper;
        this.userProfileApiService = userProfileApiService;
    }

    @Override
    public CommentResponse addComment(Long taskId, CommentAddRequest commentAddRequest) {
        Comment comment = commentAddRequestToCommentMapper.map(commentAddRequest);
        Comment createdComment = commentService.addComment(comment);
        return commentToCommentResponseMapper.map(createdComment);
    }

    @Override
    public CommentResponse updateComment(CommentEditRequest commentEditRequest) {
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

    @Override
    public void deleteComment(Long commentId) {
        commentService.deleteComment(commentId);
    }

    @Override
    public Collection<CommentResponse> getCommentsByTaskId(Long taskId) {
        Task task = taskService
                .findTaskById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Collection<Comment> comments = commentService.findCommentsByTask(task);
        return comments.stream().map(commentToCommentResponseMapper::map).toList();
    }

}
