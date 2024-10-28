package com.application.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.application.blog.security.CustomerUserDetailService;
import com.application.blog.security.JwtAuthenticationEntryPoint;
import com.application.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomerUserDetailService customerUserDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    public SecurityConfig(CustomerUserDetailService customerUserDetailService) {
        this.customerUserDetailService = customerUserDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	 http
         .csrf().disable() // Disable CSRF protection for stateless APIs
         .authorizeHttpRequests()
         .requestMatchers("/api/v1/auth/login").permitAll()
         .anyRequest().authenticated() // Require authentication for all requests
         .and()
         .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint) // Handle unauthorized access
         .and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Stateless session (no session stored)

     // Add JWT token filter before the UsernamePasswordAuthenticationFilter
     http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

     return http.build(); 
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.customerUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Using BCrypt for password hashing
    }
    
    
    @Bean  
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // Configure the authentication manager
    }
}