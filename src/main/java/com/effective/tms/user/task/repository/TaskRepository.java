package com.effective.tms.user.task.repository;

import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByAuthor(UserProfile author);
    List<Task> findAllByExecutor(UserProfile executor);
}
