package com.blogapplication.controllers;

import com.blogapplication.entities.Category;
import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.CategoryDTO;
import com.blogapplication.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO cDTO = this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<CategoryDTO>(cDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable("categoryId") Integer categoryId)
    {
        CategoryDTO categoryDTO1 = this.categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<CategoryDTO>(categoryDTO1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("categoryId") Integer categoryId)
    {
        CategoryDTO categoryDTO = this.categoryService.getCategory(categoryId);
        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public List<CategoryDTO> getAllCategory()
    {
        return this.categoryService.getAllCategories();
    }
}
