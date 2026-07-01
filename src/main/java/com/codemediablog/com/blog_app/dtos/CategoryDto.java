package com.codemediablog.com.blog_app.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {

    private Long categoryId;

    @NotBlank(message = "Category title is required")
    @Size(min = 1, message = "Category title cannot be empty")
    private String categoryTitle;

    @NotBlank(message = "Category description is required")
    @Size(min = 5, message = "Category description must be at least 100 characters")
    private String categoryDescription;
}

