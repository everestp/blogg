package com.offnine.blogg.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.offnine.blogg.Payload.JwtRequest;
import com.offnine.blogg.Payload.JwtResponse;
import com.offnine.blogg.Payload.UserDto;
import com.offnine.blogg.Services.UserService;
import com.offnine.blogg.security.JwtHelper;



import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;
    @Autowired
    private UserService userService;
  



 private Logger logger = LoggerFactory.getLogger(AuthController.class);

 


 @PostMapping("/login")
 public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
   this.doAuthenticate(request.getUsername(), request.getPassword());
     UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
     String token = this.helper.generateToken(userDetails);
     JwtResponse response = JwtResponse.builder()
     .token(token).username(userDetails.getUsername()).build();
     return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);
    
 }


 @PostMapping("/create-user")
 public ResponseEntity<UserDto> createUser( @RequestBody UserDto userDto ){
    logger.info("Attempting to create User",userDto.getEmail());
    UserDto createUserDto = this.userService.createUser(userDto);
    logger.info(" User Created successfully",userDto.getEmail());
    return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);

}
 private void doAuthenticate(String username, String password){
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
    try {
        manager.authenticate(authentication);
    } catch (BadCredentialsException e) {
        throw new BadCredentialsException("Invalid Username or password !!");
    }
 }
 @ExceptionHandler(BadCredentialsException.class)
 public String exceptionHandler(){
    return "Crendetial Invalid !!";
 }


 
}