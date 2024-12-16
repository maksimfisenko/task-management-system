package com.effective.tms.user.comment.mapper;

import com.effective.tms.common.mapper.Mapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.web.model.CommentResponse;

public interface CommentToCommentResponseMapper extends Mapper<CommentResponse, Comment> {
}
