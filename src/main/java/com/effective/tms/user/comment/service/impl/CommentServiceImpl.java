package com.effective.tms.user.comment.service.impl;

import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.repository.CommentRepository;
import com.effective.tms.user.comment.service.CommentService;
import com.effective.tms.user.task.model.Task;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public Collection<Comment> findCommentsByTask(Task task) {
        return commentRepository.findAllByTask(task);
    }
}
