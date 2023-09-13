package com.blogapplication.service_impl;

import com.blogapplication.entities.Comment;
import com.blogapplication.entities.Post;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.CommentDTO;
import com.blogapplication.repositories.CommentRepo;
import com.blogapplication.repositories.PostRepo;
import com.blogapplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));

        Comment comment = this.modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        Comment save = this.commentRepo.save(comment);
        return this.modelMapper.map(save, CommentDTO.class);
    }


    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment_id", commentId));
        this.commentRepo.delete(comment);
    }
}
