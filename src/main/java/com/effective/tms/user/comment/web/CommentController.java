package com.effective.tms.user.comment.web;

import com.effective.tms.user.comment.facade.CommentAddFacade;
import com.effective.tms.user.comment.facade.CommentDeleteFacade;
import com.effective.tms.user.comment.facade.CommentEditFacade;
import com.effective.tms.user.comment.facade.CommentFindFacade;
import com.effective.tms.user.comment.web.model.CommentAddRequest;
import com.effective.tms.user.comment.web.model.CommentEditRequest;
import com.effective.tms.user.comment.web.model.CommentResponse;
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
@RequestMapping(COMMENTS_MAPPING)
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
    @Operation(
            security = {@SecurityRequirement(name = BEARER_KEY)},
            summary = ADD_COMMENT_SUMMARY,
            tags = {COMMENTS_TAG}
    )
    @Parameter(
            name = TASK_ID_PARAM,
            description = TASK_ID_PARAM_DESC,
            schema = @Schema(type = TASK_ID_PARAM_TYPE),
            examples = @ExampleObject(value = TASK_ID_PARAM_VALUE)
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    schema = @Schema(implementation = CommentAddRequest.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = ADD_COMMENT_REQUEST_BODY)
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = STATUS_CREATED,
                    description = ADD_COMMENT_DESC_OK,
                    content = @Content(
                            schema = @Schema(implementation = CommentResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ADD_COMMENT_RESPONSE_BODY_OK)
                    )
            ),
            @ApiResponse(
                    responseCode = AUTH_RESPONSE_BODY_BAD_REQUEST,
                    description = ADD_COMMENT_DESC_BAD_REQUEST,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ADD_COMMENT_RESPONSE_BODY_BAD_REQUEST)
                    )
            )
    })
    public CommentResponse addComment(@PathVariable Long taskId,
                                      @Valid @RequestBody CommentAddRequest commentAddRequest) {
        return commentAddFacade.addComment(taskId, commentAddRequest);
    }

    @Operation(
            security = {@SecurityRequirement(name = BEARER_KEY)},
            summary = GET_COMMENTS_SUMMARY,
            tags = {COMMENTS_TAG}
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
                    description = GET_COMMENTS_DESC_OK,
                    content = @Content(
                            schema = @Schema(implementation = CommentResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = GET_COMMENTS_RESPONSE_BODY_OK)
                    )
            ),
            @ApiResponse(
                    responseCode = STATUS_BAD_REQUEST,
                    description = GET_COMMENTS_DESC_BAD_REQUEST,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = GET_COMMENTS_RESPONSE_BODY_BAD_REQUEST)
                    )
            )
    })
    @GetMapping
    public Collection<CommentResponse> findTaskComments(@PathVariable Long taskId) {
        return commentFindFacade.findCommentsByTaskId(taskId);
    }

    @Operation(
            security = {@SecurityRequirement(name = BEARER_KEY)},
            summary = EDIT_COMMENT_SUMMARY,
            tags = {COMMENTS_TAG}
    )
    @Parameter(
            name = TASK_ID_PARAM,
            description = TASK_ID_PARAM_DESC,
            schema = @Schema(type = TASK_ID_PARAM_TYPE),
            examples = @ExampleObject(value = TASK_ID_PARAM_VALUE)
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    schema = @Schema(implementation = CommentEditRequest.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = EDIT_COMMENT_REQUEST_BODY)
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = STATUS_OK,
                    description = EDIT_COMMENT_DESC_OK,
                    content = @Content(
                            schema = @Schema(implementation = CommentResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = EDIT_COMMENT_RESPONSE_BODY_OK)
                    )
            ),
            @ApiResponse(
                    responseCode = STATUS_BAD_REQUEST,
                    description = EDIT_COMMENT_DESC_BAD_REQUEST,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = EDIT_COMMENT_RESPONSE_BODY_BAD_REQUEST)
                    )
            )
    })
    @PutMapping
    public CommentResponse editComment(@Valid @RequestBody CommentEditRequest commentEditRequest) {
        return commentEditFacade.editComment(commentEditRequest);
    }

    @Operation(
            security = {@SecurityRequirement(name = BEARER_KEY)},
            summary = DELETE_COMMENT_SUMMARY,
            tags = {COMMENTS_TAG}
    )
    @Parameter(
            name = TASK_ID_PARAM,
            description = TASK_ID_PARAM_DESC,
            schema = @Schema(type = TASK_ID_PARAM_TYPE),
            examples = @ExampleObject(value = TASK_ID_PARAM_VALUE)
    )
    @Parameter(
            name = COMMENT_ID_PARAM,
            description = COMMENT_ID_PARAM_DESC,
            schema = @Schema(type = COMMENT_ID_PARAM_TYPE),
            examples = @ExampleObject(value = COMMENT_ID_PARAM_VALUE)
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = STATUS_OK,
                    description = DELETE_COMMENT_DESC_OK
            ),
            @ApiResponse(
                    responseCode = STATUS_BAD_REQUEST,
                    description = DELETE_COMMENT_DESC_BAD_REQUEST,
                    content = @Content(
                            schema = @Schema(implementation = ProblemDetail.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = DELETE_COMMENT_RESPONSE_BODY_BAD_REQUEST)
                    )
            )
    })
    @DeleteMapping(DELETE_COMMENT_MAPPING)
    public void deleteComment(@PathVariable Long commentId) {
        commentDeleteFacade.deleteComment(commentId);
    }
}
