package com.blogapplication.repositories;

import com.blogapplication.entities.Category;
import com.blogapplication.entities.Post;
import com.blogapplication.entities.User;
import com.blogapplication.payloads.PostDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String keyword);
}
