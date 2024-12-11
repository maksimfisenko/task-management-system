package com.effective.tms.user.task.service.impl;

import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.repository.TaskRepository;
import com.effective.tms.user.task.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
}
