package com.blogapplication.services;

import com.blogapplication.payloads.PostDTO;
import com.blogapplication.payloads.PostResponse;

import java.util.List;

public interface PostService {

    public PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId);

    public PostDTO updatePost(PostDTO postDTO, Integer postId);

    public void deletePost(Integer postId);

    public PostDTO getPost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    public List<PostDTO> getPostByCategory(Integer categoryId);

    public List<PostDTO> getPostByUser(Integer userId);

    public List<PostDTO> searchTitle(String keyword);


}
