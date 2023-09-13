package com.blogapplication.services;

import com.blogapplication.payloads.CommentDTO;

public interface CommentService {

    public CommentDTO createComment(CommentDTO commentDTO, Integer postId);

    public void deleteComment(Integer commentId);

}
