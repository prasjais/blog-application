package com.blogapplication.controllers;

import com.blogapplication.entities.Comment;
import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.CommentDTO;
import com.blogapplication.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable("postId") Integer postId)
    {
        CommentDTO commentDTO1 = this.commentService.createComment(commentDTO, postId);
        return new ResponseEntity<CommentDTO>(commentDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId)
    {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully", true), HttpStatus.OK);
    }



}
