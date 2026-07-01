package com.codemediablog.com.blog_app.controllers;

import com.codemediablog.com.blog_app.config.AppConstant;
import com.codemediablog.com.blog_app.dtos.ApiResponse;
import com.codemediablog.com.blog_app.dtos.PostDto;
import com.codemediablog.com.blog_app.dtos.PostResponse;
import com.codemediablog.com.blog_app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.codemediablog.com.blog_app.config.AppConstant.*;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/users/{userId}/categories/{catId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer catId) {
        PostDto createdPostDto = postService.createPost(postDto, userId, catId);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/categories/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catId) {
        List<PostDto> getPostCategory=postService.getPostByCategory(catId);
        return new ResponseEntity<>(getPostCategory, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> getPostCategory=postService.getPostByUser(userId);
        return new ResponseEntity<>(getPostCategory, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public   ResponseEntity<PostResponse> getAllPost(@RequestParam(value="pageNumber",defaultValue = PAGE_NUMBER,required = false) Integer pageNumber,
                                                     @RequestParam(value="pageSize", defaultValue = PAGE_SIZE,required = false) Integer pageSize,
                                                     @RequestParam(value="sortBy",defaultValue = SORT_BY,required = false) String sortBy)
    {
        PostResponse postDtoList=postService.getAllPost(pageNumber,pageSize,sortBy);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> getDeleteByPost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted Successfully",true, LocalDateTime.now()),HttpStatus.OK);

    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSinglePostById(@PathVariable Integer postId) {
        PostDto getPostId=postService.getPostById(postId);
        return new ResponseEntity<>(getPostId, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePostRecord(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto getPostId=postService.updatePost(postDto,postId);
        return new ResponseEntity<>(getPostId, HttpStatus.OK);

    }

}