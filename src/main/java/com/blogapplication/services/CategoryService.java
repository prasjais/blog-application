package com.blogapplication.services;

import com.blogapplication.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    public void deleteCategory(Integer categoryId);

    public CategoryDTO getCategory(Integer categoryId);

    public List<CategoryDTO> getAllCategories();
}
