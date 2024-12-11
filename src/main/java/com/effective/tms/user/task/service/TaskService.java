package com.effective.tms.user.task.service;

import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Task task);
    Optional<Task> findTaskById(Long id);
    void deleteTask(Long id);
    Collection<Task> findTasksByAuthor(UserProfile author);
    Collection<Task> findTasksByExecutor(UserProfile executor);
}
