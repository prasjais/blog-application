package com.blogapplication.controllers;

import com.blogapplication.config.AppConstants;
import com.blogapplication.entities.Post;
import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.PostDTO;
import com.blogapplication.payloads.PostResponse;
import com.blogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable("categoryId") Integer categoryId, @PathVariable("userId") Integer userId)
    {
        PostDTO postDTO1 = this.postService.createPost(postDTO,categoryId, userId);
        return new ResponseEntity<PostDTO>(postDTO1, HttpStatus.CREATED);
    }

    //get list of post by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable("categoryId") Integer categoryId)
    {
        List<PostDTO> postDTO = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(postDTO, HttpStatus.OK);
    }

    //get list of post by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable("userId") Integer userId)
    {
        List<PostDTO> postDTOS = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDTO>>(postDTOS, HttpStatus.OK);
    }

    //to get single post
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("postId") Integer postId)
    {
        PostDTO postDTO = this.postService.getPost(postId);
        return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
    }

    //to get all post
    @GetMapping("/")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
    {
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId)
    {
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
    }

    //to update some post
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId)
    {
        PostDTO postDTO1 = this.postService.updatePost(postDTO, postId);
        return  new ResponseEntity<PostDTO>(postDTO1, HttpStatus.OK);
    }

    //search feature
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDTO>> getPostByTitle(@PathVariable("keyword") String keyword)
    {
        List<PostDTO> postDTOS = this.postService.searchTitle(keyword);
        return new ResponseEntity<List<PostDTO>>(postDTOS, HttpStatus.OK);
    }



}
