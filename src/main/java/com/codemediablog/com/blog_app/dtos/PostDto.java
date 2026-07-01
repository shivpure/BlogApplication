package com.codemediablog.com.blog_app.dtos;

import com.codemediablog.com.blog_app.entities.Category;
import com.codemediablog.com.blog_app.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class PostDto {

    private Long postId;
    private String title;
    private String content;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
}
