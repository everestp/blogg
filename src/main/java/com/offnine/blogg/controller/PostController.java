 package com.offnine.blogg.controller;
// video 20


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offnine.blogg.Payload.ApiResponse;
import com.offnine.blogg.Payload.PostDto;
import com.offnine.blogg.Payload.PostResponse;
import com.offnine.blogg.Services.PostService;



@RestController
@RequestMapping("/api")
 public class PostController {
    @Autowired
 private PostService postService;



 

 // create post
 @PostMapping("/user/{userId}/category/{categoryId}/posts")
 public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,@PathVariable Integer userId, @PathVariable Integer categoryId){
    PostDto createPost =  this.postService.createPost(postDto, userId, categoryId);
     return new ResponseEntity<PostDto>(createPost ,HttpStatus.CREATED);

 }

 //upadatesd post
 @PutMapping("/posts/{postId}")
public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
   PostDto createPost = this.postService.upadatePost(postDto, postId);
   return new ResponseEntity<>(createPost,HttpStatus.CREATED);
   
}

 // --> get post by Id
@GetMapping("/post/{postId}")
 public ResponseEntity<PostDto> getPostbyID(@PathVariable Integer postId){
   PostDto postdto =this.postService.getPostById(postId);
   return new ResponseEntity<PostDto>(postdto,HttpStatus.OK);
 }
 @GetMapping("/category/{categoryId}/posts")
 public ResponseEntity<List<PostDto>>  getPostByCategory(@PathVariable Integer categoryId){
   List <PostDto> posts =this.postService.getPostByCategory(categoryId);
   return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

}
@GetMapping("/user/{userId}/posts")
public ResponseEntity<List<PostDto>>  getPostByUser(@PathVariable Integer userId){
  List <PostDto> posts =this.postService.getPostByUser(userId);
  return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

}
@DeleteMapping("/posts/{postId}")
public ApiResponse deletePost(@PathVariable Integer postId){
   this.postService.deletePost(postId);
   return new ApiResponse("Post is Successfully Deleted",true);
}

//get all post
@GetMapping("/posts")
public ResponseEntity <PostResponse> getAllPost(
   @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
   @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize

){
   PostResponse postResponse =this.postService.getAllPosts(pageNumber,pageSize);
   return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);}
 }