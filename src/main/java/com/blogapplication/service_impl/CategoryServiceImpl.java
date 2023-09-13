package com.blogapplication.service_impl;

import com.blogapplication.entities.Category;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.CategoryDTO;
import com.blogapplication.repositories.CategoryRepo;
import com.blogapplication.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = this.modelMapper.map(categoryDTO, Category.class);
        Category saved = this.categoryRepo.save(category);
        return this.modelMapper.map(saved, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId", categoryId));

        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

        Category saved = this.categoryRepo.save(category);

        return this.modelMapper.map(saved, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<Category> catList = this.categoryRepo.findAll();
        List<CategoryDTO> list = catList.stream().map(cat->this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return list;
    }
}
