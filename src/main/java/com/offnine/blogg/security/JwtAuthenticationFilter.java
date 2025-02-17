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
   
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired 
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getServletPath();

        // ✅ FIXED: Skipping authentication only for `/auth` endpoints 
        if (requestPath.contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestHeader = request.getHeader("Authorization");

        // ✅ FIXED: Properly logging the authorization header value
        logger.info("Authorization Header: {}", requestHeader);

        String username = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            // ✅ FIXED: Extracting token correctly (removing "Bearer " prefix)
            token = requestHeader.substring(7);
            try {
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.error("Illegal argument while fetching the username from token!", e);
            } catch (ExpiredJwtException e) {
                logger.warn("Token Expired!", e);
            } catch (Exception e) {
                logger.error("Error extracting username from token!", e);
            }
        } else {
            logger.warn("Invalid Authorization header!");
        }

        // ✅ FIXED: Authentication logic - Checking if token is valid
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            boolean isValidToken = this.jwtHelper.validateToken(token, userDetails);

            if (isValidToken) {
                // ✅ FIXED: `validateToken` should not be passed as the credentials, use `null`
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // ✅ FIXED: Added a log message to confirm authentication
                logger.info("User authenticated: {}", username);
            } else {
                logger.warn("Token validation failed!");
            }
        }

        // ✅ FIXED: Ensuring the filter chain continues even if authentication fails
        filterChain.doFilter(request, response);
    }
}
