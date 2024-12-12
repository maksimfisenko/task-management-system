package com.effective.tms.user.comment.mapper.impl;

import com.effective.tms.user.comment.mapper.CommentAddRequestToCommentMapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.service.CommentService;
import com.effective.tms.user.comment.web.model.CommentAddRequest;
import com.effective.tms.user.profile.api.service.UserProfileApiService;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.service.TaskService;
import org.springframework.stereotype.Component;

@Component
public class CommentAddRequestToCommentMapperImpl implements CommentAddRequestToCommentMapper {

    private final UserProfileApiService userProfileApiService;
    private final TaskService taskService;

    public CommentAddRequestToCommentMapperImpl(UserProfileApiService userProfileApiService, TaskService taskService) {
        this.userProfileApiService = userProfileApiService;
        this.taskService = taskService;
    }

    @Override
    public Comment map(CommentAddRequest commentAddRequest) {

        UserProfile currentUser = userProfileApiService.getCurrentUserProfile();
        Task task = taskService
                .findTaskById(commentAddRequest.taskId())
                .orElseThrow(() -> new RuntimeException("Task with this id doesn't exist"));

        Comment comment = new Comment();
        comment.setMessage(commentAddRequest.message());
        comment.setAuthor(currentUser);
        comment.setTask(task);

        return comment;
    }
}
