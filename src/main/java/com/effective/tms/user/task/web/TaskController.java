package com.effective.tms.user.task.web;

import com.effective.tms.user.task.facade.TaskAddFacade;
import com.effective.tms.user.task.facade.TaskDeleteFacade;
import com.effective.tms.user.task.facade.TaskEditFacade;
import com.effective.tms.user.task.facade.TaskFindFacade;
import com.effective.tms.user.task.web.model.TaskAddRequest;
import com.effective.tms.user.task.web.model.TaskEditRequest;
import com.effective.tms.user.task.web.model.TaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
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
    @Operation(
            security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Create a new task",
            tags = {"Tasks"}
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    schema = @Schema(implementation = TaskAddRequest.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "title": "Write unit tests",
                                        "description": "Unit tests need to be written",
                                        "status": "WAITING",
                                        "priority": "HIGH",
                                        "executorId": 1
                                    }
                                    """
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully created new task",
                    content = @Content(
                            schema = @Schema(implementation = TaskResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 8,
                                                "title": "Write unit tests",
                                                "description": "Unit tests need to be written",
                                                "status": "WAITING",
                                                "priority": "HIGH",
                                                "authorId": 6,
                                                "executorId": 1,
                                                "createdTimestamp": "2024-12-13T15:49:00.219090300Z",
                                                "modifiedTimestamp": "2024-12-13T15:49:00.219090300Z"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Not valid request",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "type": "localhost:8080/api/v1",
                                                "title": "Bad Request",
                                                "status": 400,
                                                "detail": "title must not be blank",
                                                "instance": "/api/v1/tasks"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Current user does not have permission to create a new task",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "type": "localhost:8080/api/v1",
                                                 "title": "Bad Request",
                                                 "status": 400,
                                                 "detail": "Current user does not have permission to add task",
                                                 "instance": "/api/v1/tasks"
                                             }
                                            """
                            )
                    )
            )
    })
    public TaskResponse createTask(@Valid @RequestBody TaskAddRequest taskAddRequest) {
        return taskAddFacade.createTask(taskAddRequest);
    }

    @PutMapping
    public TaskResponse editTask(@Valid @RequestBody TaskEditRequest taskEditRequest) {
        return taskEditFacade.editTask(taskEditRequest);
    }

    @DeleteMapping("/{taskId}")
    @Operation(
            security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Delete task by task ID",
            tags = {"Tasks"}
    )
    @Parameter(
            name = "taskId",
            description = "ID of the task to delete",
            schema = @Schema(type = "Long"),
            examples = @ExampleObject(value = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted task"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad task ID given",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "type": "localhost:8080/api/v1",
                                                "title": "Bad Request",
                                                "status": 400,
                                                "detail": "Method parameter 'taskId': Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \\"rr\\"",
                                                "instance": "/api/v1/tasks/rr"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User doesn't have authorities to delete task",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "type": "localhost:8080/api/v1",
                                                 "title": "Bad Request",
                                                 "status": 400,
                                                 "detail": "Current user does not have permission to delete task",
                                                 "instance": "/api/v1/tasks"
                                             }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to delete task with given id",
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "type": "localhost:8080/api/v1",
                                                "title": "Bad Request",
                                                "status": 400,
                                                "detail": "Task with id = -1 doesn't exist",
                                                "instance": "/api/v1/tasks/-1"
                                            }
                                            """
                            )
                    )
            ),
    })
    public void deleteTask(@PathVariable Long taskId) {
        taskDeleteFacade.deleteTask(taskId);
    }

    @GetMapping
    @Operation(
            security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Get all tasks of the current user",
            tags = {"Tasks"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returned all current user's tasks",
                    content = @Content(
                            schema = @Schema(
                                    type = "array",
                                    implementation = TaskResponse.class
                            ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    value = """
                                            [
                                                 {
                                                     "id": 7,
                                                     "title": "Task 1",
                                                     "description": "Description 1",
                                                     "status": "WAITING",
                                                     "priority": "HIGH",
                                                     "authorId": 6,
                                                     "executorId": 1,
                                                     "createdTimestamp": "2024-12-13T15:38:54.809545Z",
                                                     "modifiedTimestamp": "2024-12-13T15:38:54.809545Z"
                                                 },
                                                 {
                                                     "id": 8,
                                                     "title": "Task 2",
                                                     "description": "Description 2",
                                                     "status": "COMPLETED",
                                                     "priority": "LOW",
                                                     "authorId": 6,
                                                     "executorId": 2,
                                                     "createdTimestamp": "2024-12-13T15:49:00.219090Z",
                                                     "modifiedTimestamp": "2024-12-13T15:49:00.219090Z"
                                                 }
                                             ]
                                            """
                            )
                    )
            )
    })
    public Collection<TaskResponse> findUserTasks() {
        return taskFindFacade.findTasks();
    }

}
