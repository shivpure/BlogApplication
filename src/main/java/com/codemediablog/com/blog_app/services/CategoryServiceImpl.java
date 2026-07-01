package com.codemediablog.com.blog_app.services;

import com.codemediablog.com.blog_app.dtos.ApiResponse;
import com.codemediablog.com.blog_app.dtos.CategoryDto;
import com.codemediablog.com.blog_app.entities.Category;
import com.codemediablog.com.blog_app.exceptions.ResourceNotFoundException;
import com.codemediablog.com.blog_app.repositories.CategoryRepo;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category cat=categoryRepo.save(category);

        return modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCat=categoryRepo.save(category);
        return modelMapper.map(updatedCat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory(CategoryDto categoryDto) {
        List<Category> getAllCategory = categoryRepo.findAll();
        return getAllCategory.stream().map(category->modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }
}
