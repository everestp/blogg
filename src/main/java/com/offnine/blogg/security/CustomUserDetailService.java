package com.offnine.blogg.security;

import com.offnine.blogg.Repo.UserRepo;
import com.offnine.blogg.entities.User;
import com.offnine.blogg.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

@Autowired
private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      //loading user From database
      User user =  this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","email :" +username,0));
       return user;

    }
}