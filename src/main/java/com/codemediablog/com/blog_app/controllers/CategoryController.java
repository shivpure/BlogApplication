package com.codemediablog.com.blog_app.controllers;


import com.codemediablog.com.blog_app.dtos.ApiResponse;
import com.codemediablog.com.blog_app.dtos.CategoryDto;
import com.codemediablog.com.blog_app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategoryDto=categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategoryDto,HttpStatus.CREATED);
    }

    @GetMapping("/{catId}")
    public  ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer categoryId){
        CategoryDto categoryDto=categoryService.getCategory(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @GetMapping("/")
    public   ResponseEntity<List<CategoryDto>> getAllCategory(CategoryDto categoryDto){
        List<CategoryDto> categoryDtoList=categoryService.getAllCategory(categoryDto);
        return new ResponseEntity<List<CategoryDto>>(categoryDtoList,HttpStatus.OK);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("catId") Integer categoryId){
        CategoryDto updateCategory=categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
   public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer categoryId){
        categoryService.deleteCategory(categoryId);
       return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully",true, LocalDateTime.now()),HttpStatus.OK);
   }

}
