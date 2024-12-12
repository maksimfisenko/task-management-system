package com.effective.tms.user.task.web;

import com.effective.tms.user.task.facade.TaskAddFacade;
import com.effective.tms.user.task.facade.TaskDeleteFacade;
import com.effective.tms.user.task.facade.TaskEditFacade;
import com.effective.tms.user.task.facade.TaskFindFacade;
import com.effective.tms.user.task.web.model.TaskAddRequest;
import com.effective.tms.user.task.web.model.TaskEditRequest;
import com.effective.tms.user.task.web.model.TaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskAddFacade taskAddFacade;
    private final TaskEditFacade taskEditFacade;
    private final TaskDeleteFacade taskDeleteFacade;
    private final TaskFindFacade taskFindFacade;

    public TaskController(TaskAddFacade taskAddFacade,
                          TaskEditFacade taskEditFacade,
                          TaskDeleteFacade taskDeleteFacade,
                          TaskFindFacade taskFindFacade) {
        this.taskAddFacade = taskAddFacade;
        this.taskEditFacade = taskEditFacade;
        this.taskDeleteFacade = taskDeleteFacade;
        this.taskFindFacade = taskFindFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody TaskAddRequest taskAddRequest) {
        return taskAddFacade.createTask(taskAddRequest);
    }

    @PutMapping
    public TaskResponse editTask(@Valid @RequestBody TaskEditRequest taskEditRequest) {
        return taskEditFacade.editTask(taskEditRequest);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskDeleteFacade.deleteTask(taskId);
    }

    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public Collection<TaskResponse> findUserTasks() {
        return taskFindFacade.findTasks();
    }

}
