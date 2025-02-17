package com.offnine.blogg.security;

import java.io.IOException;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   
    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private JwtHelper jwtHelper;
    @Autowired 
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        logger.info("header :{}" );
        String username = null;
        String token = null;
        System.out.println(requestHeader);
        if(requestHeader != null && requestHeader.startsWith("Bearer")){
            // loking good
            token = requestHeader.substring(7);
            try {
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
               logger.info("Illegal Artgument whiel fetching the username !!");
               e.printStackTrace();
            }catch(ExpiredJwtException e){
                logger.info("Token Expired !!");
               e.printStackTrace();

            } catch(Exception e){
                e.printStackTrace();
            }

        } 
        else{
            logger.info("Invalid Header value !!");
        }
        if( username !=null && SecurityContextHolder.getContext().getAuthentication()==null){


            // fetched user details
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean  validateToken = this.jwtHelper.validateToken(token, userDetails);
            if(validateToken){
                //  set Authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, validateToken);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            else{
                logger.info("Validation of the token failed");
            }
        }
filterChain.doFilter(request, response);
    }
    
}
