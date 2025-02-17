package com.offnine.blogg.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.offnine.blogg.security.JwtAuthenticationEntryPoint;
import com.offnine.blogg.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
@Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
   private  JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

   @Autowired
   private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
      // confuigration
      http.csrf(csrf -> csrf.disable())
      .cors(cors -> cors.disable())
      .authorizeHttpRequests(
        auth ->
        auth.requestMatchers("/api/users/**").authenticated()
        .requestMatchers("/auth/login").permitAll()
        .requestMatchers("/auth/create-user").permitAll().anyRequest()
        .authenticated())
        
      .exceptionHandling(ex->ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
      .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
          return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAutheticatorProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
            return provider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
