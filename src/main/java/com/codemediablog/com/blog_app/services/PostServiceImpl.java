package com.codemediablog.com.blog_app.services;

import com.codemediablog.com.blog_app.dtos.CategoryDto;
import com.codemediablog.com.blog_app.dtos.PostDto;
import com.codemediablog.com.blog_app.dtos.PostResponse;
import com.codemediablog.com.blog_app.dtos.UserDto;
import com.codemediablog.com.blog_app.entities.Category;
import com.codemediablog.com.blog_app.entities.Post;
import com.codemediablog.com.blog_app.entities.User;
import com.codemediablog.com.blog_app.exceptions.ResourceNotFoundException;
import com.codemediablog.com.blog_app.repositories.CategoryRepo;
import com.codemediablog.com.blog_app.repositories.PostRepo;
import com.codemediablog.com.blog_app.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    PostResponse postResponse=new PostResponse();



    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        Post post=modelMapper.map(postDto, Post.class);
        post.setAddedDate(new Date());
        post.setImage("default.png");
        post.setUser(user);
        post.setCategory(category);
        Post craetedPost=postRepo.save(post);
        return modelMapper.map(craetedPost,PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto postDto,Integer postId) {
       Post updatePost= postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
       updatePost.setPostTitle(postDto.getTitle());
       updatePost.setContent(postDto.getContent());
       Post updatePostById=postRepo.save(updatePost);
       return modelMapper.map(updatePostById,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post deletePost= postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        postRepo.delete(deletePost);
    }

//    implementing paging and sorting  to getting  all post record

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy) {
        Pageable getPagePost= PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));
        Page<Post> getPostData= postRepo.findAll(getPagePost);
        List<Post> getallPostList=getPostData.getContent();
        List<PostDto> postDtoPost=getallPostList.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        postResponse.setContent(postDtoPost);
        postResponse.setPageSize(getPostData.getSize());
        postResponse.setPageNumber(getPostData.getNumber());
        postResponse.setTotalPages(getPostData.getTotalPages());
        postResponse.setTotalElement(getPostData.getTotalElements());
        postResponse.setTotalPages(getPostData.getTotalPages());
        postResponse.setLastPage(getPostData.isLast());
        return postResponse;
    }



    @Override
    public PostDto getPostById(Integer postId) {
              Post getSingleRecord= postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        return modelMapper.map(getSingleRecord,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
     Category getCategoryID = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post> getListOfCategory=postRepo.findByCategory(getCategoryID);
        return getListOfCategory.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        List<Post> getPostData=postRepo.findByUser(user);
        return getPostData.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }
}
