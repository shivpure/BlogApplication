package com.codemediablog.com.blog_app.services;

import com.codemediablog.com.blog_app.dtos.CategoryDto;
import com.codemediablog.com.blog_app.dtos.UserDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    void deleteCategory(Integer categoryId);
    CategoryDto getCategory(Integer categoryId);
    List<CategoryDto> getAllCategory(CategoryDto categoryDto);
}
