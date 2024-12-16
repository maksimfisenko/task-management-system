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

import static com.effective.tms.common.constants.ApiConstants.*;

@Slf4j
@RestController
@RequestMapping(TASKS_MAPPING)
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
            security = {@SecurityRequirement(name = BEARER_KEY)},
            summary = ADD_TASK_SUMMARY,
            tags = {TASKS_TAG}
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    schema = @Schema(implementation = TaskAddRequest.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = ADD_TASK_REQUEST_BODY)
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = STATUS_CREATED,
                    description = ADD_TASK_DESC_OK,
                    content = @Content(
                            schema = @Schema(implementation = TaskResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ADD_TASK_RESPONSE_BODY_OK)
                    )
            ),
            @ApiResponse(
                    responseCode = STATUS_BAD_REQUEST,
                    description = ADD_TASK_DESC_BAD_REQUEST,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ADD_TASK_RESPONSE_BODY_BAD_REQUEST)
                    )
            ),
            @ApiResponse(
                    responseCode = STATUS_UNAUTHORIZED,
                    description = ADD_TASK_DESC_UNAUTHORIZED,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ADD_TASK_RESPONSE_BODY_UNAUTHORIZED)
                    )
            )
    })
    public TaskResponse addTask(@Valid @RequestBody TaskAddRequest taskAddRequest) {
        return taskAddFacade.createTask(taskAddRequest);
    }

    @PutMapping
    public TaskResponse editTask(@Valid @RequestBody TaskEditRequest taskEditRequest) {
        return taskEditFacade.editTask(taskEditRequest);
    }

    @DeleteMapping(DELETE_TASK_MAPPING)
    @Operation(
            security = {@SecurityRequirement(name = BEARER_KEY)},
            summary = DELETE_TASK_SUMMARY,
            tags = {TASKS_TAG}
    )
    @Parameter(
            name = TASK_ID_PARAM,
            description = TASK_ID_PARAM_DESC,
            schema = @Schema(type = TASK_ID_PARAM_TYPE),
            examples = @ExampleObject(value = TASK_ID_PARAM_VALUE)
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = STATUS_OK,
                    description = DELETE_TASK_DESC_OK
            ),
            @ApiResponse(
                    responseCode = STATUS_BAD_REQUEST,
                    description = DELETE_TASK_DESC_BAD_REQUEST,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = DELETE_TASK_RESPONSE_BODY_BAD_REQUEST)
                    )
            ),
            @ApiResponse(
                    responseCode = STATUS_UNAUTHORIZED,
                    description = DELETE_TASK_DESC_UNAUTHORIZED,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = DELETE_TASK_RESPONSE_BODY_UNAUTHORIZED)
                    )
            ),
            @ApiResponse(
                    responseCode = STATUS_INTERNAL_ERROR,
                    description = DELETE_TASK_DESC_INTERNAL_ERROR,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = DELETE_TASK_RESPONSE_BODY_INTERNAL_ERROR)
                    )
            ),
    })
    public void deleteTask(@PathVariable Long taskId) {
        taskDeleteFacade.deleteTask(taskId);
    }

    @GetMapping
    @Operation(
            security = {@SecurityRequirement(name = BEARER_KEY)},
            summary = GET_TASKS_SUMMARY,
            tags = {TASKS_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = STATUS_OK,
                    description = GET_TASKS_DESC_OK,
                    content = @Content(
                            schema = @Schema(type = ARRAY_TYPE, implementation = TaskResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = GET_TASKS_RESPONSE_BODY_OK)
                    )
            )
    })
    public Collection<TaskResponse> findUserTasks() {
        return taskFindFacade.findTasks();
    }

}
