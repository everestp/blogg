package com.offnine.blogg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.offnine.blogg.Payload.ApiResponse;
import com.offnine.blogg.Payload.CommentDto;
import com.offnine.blogg.Services.CommentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

@PostMapping("/posts/{postId}/comments")
public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId) {
   CommentDto createdComment = this.commentService.createComment(comment, postId);
   return  new ResponseEntity<CommentDto>(createdComment,HttpStatus.CREATED);
    
   
}
@DeleteMapping("/delete/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
   return new ResponseEntity<ApiResponse>( new ApiResponse("Comment is Sucessfully Deleted",true),HttpStatus.OK);
    }




}



