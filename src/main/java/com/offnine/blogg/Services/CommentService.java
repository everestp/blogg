package com.offnine.blogg.Services;


import org.springframework.stereotype.Service;

import com.offnine.blogg.Payload.CommentDto;
@Service
public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
