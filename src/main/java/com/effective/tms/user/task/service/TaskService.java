package com.effective.tms.user.task.service;

import com.effective.tms.user.task.model.Task;

import java.util.Optional;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Task task);
    Optional<Task> findTaskById(Long id);
    void deleteTask(Long id);
}
