package com.effective.tms.user.comment.mapper;

import com.effective.tms.security.mapper.Mapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.web.model.CommentAddRequest;

public interface CommentAddRequestToCommentMapper extends Mapper<Comment, CommentAddRequest> {
}