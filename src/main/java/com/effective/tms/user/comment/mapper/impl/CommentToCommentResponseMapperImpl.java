package com.effective.tms.user.comment.mapper.impl;

import com.effective.tms.user.comment.mapper.CommentToCommentResponseMapper;
import com.effective.tms.user.comment.model.Comment;
import com.effective.tms.user.comment.web.model.CommentResponse;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentResponseMapperImpl implements CommentToCommentResponseMapper {
    @Override
    public CommentResponse map(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getMessage(),
                comment.getTask().getId(),
                comment.getAuthor().getId(),
                comment.getCreatedTimestamp(),
                comment.getModifiedTimestamp()
        );
    }
}
