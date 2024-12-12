package com.effective.tms.user.comment.repository;

import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTask(Task task);
}
