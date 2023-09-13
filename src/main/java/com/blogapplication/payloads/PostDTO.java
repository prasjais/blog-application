package com.blogapplication.payloads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private int postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDTO category;
    private UserDTO user;
    private Set<CommentDTO> comments = new HashSet<>();
}
