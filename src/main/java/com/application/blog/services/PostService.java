package com.application.blog.services;

import java.util.List;

import com.application.blog.payloads.PostDto;
import com.application.blog.payloads.PostResponse;

public interface PostService {

    // Create
   public  PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    
    // Update
    PostDto updatePost(PostDto postDto, Integer postId);
    
    // Delete a post
    void deletePost(Integer postId);
    
    // Get all posts
    PostResponse getAllPost(Integer pageNumber,Integer pageSize ,String sortB,String sortIDir);
    
    // Get single post by ID
    PostDto getPostById(Integer postId);
    
    // Get posts by category
    List<PostDto> getPostByCategory(Integer categoryId);
    
    // Get posts by user
    List<PostDto> getPostByUser(Integer userId);
    
    // Search posts by keyword
    List<PostDto> searchPosts(String keyword);
}
