package com.offnine.blogg.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.offnine.blogg.Payload.PostDto;
import com.offnine.blogg.Payload.PostResponse;

@Service
public interface PostService {
    // video 19 ==> Creating post service Entity

    // create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);


    //update
PostDto upadatePost(PostDto postDto ,Integer postId);

    // delete
    void deletePost(Integer postId);

    //get all post
    PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    // get single post
    PostDto getPostById(Integer postId);

    // get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //get all post by user
    List<PostDto> getPostByUser(Integer userId);

   // search post
   List<PostDto> searchPosts(String keyword);

    
}
