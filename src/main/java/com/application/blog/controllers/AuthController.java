package com.application.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.blog.payloads.JwtAuthRequest;
import com.application.blog.payloads.JwtAuthResponse;
import com.application.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	
	    @Autowired
	    private JwtTokenHelper jwtTokenHelper;
	    
	    @Autowired
	    private UserDetailsService userDetailsService;
	

	    @Autowired
	    private AuthenticationManager authenticationManager;
	    
	    @PostMapping("/login")
	    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {
	        // Authenticate the user
	        this.authenticate(request.getUsername(), request.getPassword());

	        // Load user details to generate the token
	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

	        // Generate the token
	        String token = jwtTokenHelper.generateToken(userDetails);

	        // Create response containing the JWT token
	        JwtAuthResponse response = new JwtAuthResponse();
	        response.setToken(token);

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    private void authenticate(String username, String password) {
	        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
	        authenticationManager.authenticate(authenticationToken);
	    }
	}

