package com.effective.tms.user.task.web;

import com.effective.tms.user.task.facade.TaskAddFacade;
import com.effective.tms.user.task.web.model.TaskAddRequest;
import com.effective.tms.user.task.web.model.TaskResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskAddFacade taskAddFacade;

    public TaskController(TaskAddFacade taskAddFacade) {
        this.taskAddFacade = taskAddFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody TaskAddRequest taskAddRequest) {
        return taskAddFacade.createTask(taskAddRequest);
    }
}
