package com.blogapplication.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private int categoryId;

    @NotEmpty(message = "Title cannot be empty")
//    @Pattern(regexp = "^[a-zA-Z]", message = "Something must be written")
    private String categoryTitle;

    @NotEmpty(message = "Description must be given")
    private String categoryDescription;
}
