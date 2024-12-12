package com.effective.tms.user.task.web;

import com.effective.tms.user.comment.facade.CommentFacade;
import com.effective.tms.user.comment.web.model.CommentAddRequest;
import com.effective.tms.user.comment.web.model.CommentEditRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;
import com.effective.tms.user.task.facade.TaskAddFacade;
import com.effective.tms.user.task.facade.TaskDeleteFacade;
import com.effective.tms.user.task.facade.TaskEditFacade;
import com.effective.tms.user.task.facade.TaskFindFacade;
import com.effective.tms.user.task.web.model.TaskAddRequest;
import com.effective.tms.user.task.web.model.TaskEditRequest;
import com.effective.tms.user.task.web.model.TaskResponse;
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
    private final CommentFacade commentFacade;

    public TaskController(TaskAddFacade taskAddFacade,
                          TaskEditFacade taskEditFacade,
                          TaskDeleteFacade taskDeleteFacade,
                          TaskFindFacade taskFindFacade,
                          CommentFacade commentFacade) {
        this.taskAddFacade = taskAddFacade;
        this.taskEditFacade = taskEditFacade;
        this.taskDeleteFacade = taskDeleteFacade;
        this.taskFindFacade = taskFindFacade;
        this.commentFacade = commentFacade;
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
    public Collection<TaskResponse> findUserTasks() {
        return taskFindFacade.findTasks();
    }

    @PostMapping("/{taskId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse addComment(@PathVariable Long taskId,
                                      @Valid @RequestBody CommentAddRequest commentAddRequest) {
        return commentFacade.addComment(taskId, commentAddRequest);
    }

    @GetMapping("/{taskId}/comments")
    public Collection<CommentResponse> findTaskComments(@PathVariable Long taskId) {
        return commentFacade.getCommentsByTaskId(taskId);
    }

    @PutMapping("/{taskId}/comments")
    public CommentResponse editComment(@Valid @RequestBody CommentEditRequest commentEditRequest) {
        return commentFacade.updateComment(commentEditRequest);
    }

    @DeleteMapping("/{taskId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentFacade.deleteComment(commentId);
    }

}
