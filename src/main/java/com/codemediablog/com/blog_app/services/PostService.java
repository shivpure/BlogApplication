package com.codemediablog.com.blog_app.services;

import com.codemediablog.com.blog_app.dtos.CategoryDto;
import com.codemediablog.com.blog_app.dtos.PostDto;
import com.codemediablog.com.blog_app.dtos.PostResponse;
import com.codemediablog.com.blog_app.dtos.UserDto;
import com.codemediablog.com.blog_app.entities.Category;
import com.codemediablog.com.blog_app.entities.Post;
import com.codemediablog.com.blog_app.entities.User;

import java.util.List;

public interface PostService {
    
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy);
    PostDto getPostById(Integer postId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);

    
}
