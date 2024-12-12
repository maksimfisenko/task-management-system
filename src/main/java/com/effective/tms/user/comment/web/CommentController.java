package com.effective.tms.user.comment.web;

import com.effective.tms.user.comment.facade.CommentAddFacade;
import com.effective.tms.user.comment.facade.CommentDeleteFacade;
import com.effective.tms.user.comment.facade.CommentEditFacade;
import com.effective.tms.user.comment.facade.CommentFindFacade;
import com.effective.tms.user.comment.web.model.CommentAddRequest;
import com.effective.tms.user.comment.web.model.CommentEditRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/v1/tasks/{taskId}/comments")
public class CommentController {

    private final CommentAddFacade commentAddFacade;
    private final CommentEditFacade commentEditFacade;
    private final CommentDeleteFacade commentDeleteFacade;
    private final CommentFindFacade commentFindFacade;

    public CommentController(CommentAddFacade commentAddFacade,
                             CommentEditFacade commentEditFacade,
                             CommentDeleteFacade commentDeleteFacade,
                             CommentFindFacade commentFindFacade) {
        this.commentAddFacade = commentAddFacade;
        this.commentEditFacade = commentEditFacade;
        this.commentDeleteFacade = commentDeleteFacade;
        this.commentFindFacade = commentFindFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse addComment(@PathVariable Long taskId,
                                      @Valid @RequestBody CommentAddRequest commentAddRequest) {
        return commentAddFacade.addComment(taskId, commentAddRequest);
    }

    @GetMapping
    public Collection<CommentResponse> findTaskComments(@PathVariable Long taskId) {
        return commentFindFacade.findCommentsByTaskId(taskId);
    }

    @PutMapping
    public CommentResponse editComment(@Valid @RequestBody CommentEditRequest commentEditRequest) {
        return commentEditFacade.editComment(commentEditRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentDeleteFacade.deleteComment(commentId);
    }
}
