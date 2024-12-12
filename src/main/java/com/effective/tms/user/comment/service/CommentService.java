package com.effective.tms.user.comment.service;

import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.task.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface CommentService {
    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
    void deleteComment(Long commentId);
    Optional<Comment> findById(Long commentId);
    Collection<Comment> findCommentsByTask(Task task);
}
