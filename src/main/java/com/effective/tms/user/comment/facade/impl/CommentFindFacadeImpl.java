package com.effective.tms.user.comment.facade.impl;

import com.effective.tms.user.comment.facade.CommentFindFacade;
import com.effective.tms.user.comment.mapper.CommentToCommentResponseMapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.service.CommentService;
import com.effective.tms.user.comment.web.model.CommentResponse;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CommentFindFacadeImpl implements CommentFindFacade {
    private final TaskService taskService;
    private final CommentService commentService;
    private final CommentToCommentResponseMapper commentToCommentResponseMapper;

    public CommentFindFacadeImpl(TaskService taskService,
                                 CommentService commentService,
                                 CommentToCommentResponseMapper commentToCommentResponseMapper) {
        this.taskService = taskService;
        this.commentService = commentService;
        this.commentToCommentResponseMapper = commentToCommentResponseMapper;
    }

    @Override
    public Collection<CommentResponse> findCommentsByTaskId(Long taskId) {
        Task task = taskService
                .findTaskById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Collection<Comment> comments = commentService.findCommentsByTask(task);
        return comments.stream().map(commentToCommentResponseMapper::map).toList();
    }
}
