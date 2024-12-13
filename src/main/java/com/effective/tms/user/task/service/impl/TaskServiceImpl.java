package com.effective.tms.user.task.service.impl;

import com.effective.tms.common.TmsException;
import com.effective.tms.user.profile.model.UserProfile;
import com.effective.tms.user.task.model.Task;
import com.effective.tms.user.task.repository.TaskRepository;
import com.effective.tms.user.task.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TmsException(String.format("Task with id = %s doesn't exist", id));
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Collection<Task> findTasksByAuthor(UserProfile author) {
        return taskRepository.findAllByAuthor(author);
    }

    @Override
    public Collection<Task> findTasksByExecutor(UserProfile executor) {
        return taskRepository.findAllByExecutor(executor);
    }
}
