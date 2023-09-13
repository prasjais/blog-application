package com.blogapplication.payloads;

import com.blogapplication.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDTO> content;
    private int pageNumber;
    private int pageSize;
    private Long totalRecords;
    private int totalPages;
    private boolean isLastPage;
}
