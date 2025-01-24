package com.offnine.blogg.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offnine.blogg.Payload.UserDto;
import com.offnine.blogg.Payload.ApiResponse;
import com.offnine.blogg.Services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
private UserService userService;

@PostMapping("/")

public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto ){
    UserDto createUserDto = this.userService.createUser(userDto);
    return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);

}

@PutMapping("/{userId}")
public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
    UserDto updateUser = this.userService.updateUser(userDto, userId);
    return ResponseEntity.ok(updateUser);
}

// get all user
@GetMapping("/")
public ResponseEntity<List<UserDto>> getAllUsers(){
   return ResponseEntity.ok(this.userService.getAllUsers());
    
}

// --> get single user
@GetMapping("/{userId}")
 public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
    return ResponseEntity.ok(this.userService.getUserById(userId));
 }

// delete user
@DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
   this.userService.deleteUser(userId);
   return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Sucessfully",true),HttpStatus.OK);
}
}