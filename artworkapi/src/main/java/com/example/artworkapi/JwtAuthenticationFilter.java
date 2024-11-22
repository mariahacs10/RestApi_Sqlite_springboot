package com.example.artworkapi;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.artworkapi.service.JwtService;
import com.example.artworkapi.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//The JwtAuthenticationFilter class is a custom security filter that intercepts every request once and checks for a JWT token in the Authorization header.
//It extends OncePerRequestFilter, meaning it is executed once per request.

//This filter plays a critical role in extracting and validating JWT tokens and setting the appropriate security context for requests in your application.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

 @Autowired
 private JwtService jwtService;  // Service to handle JWT-related operations (e.g., token validation, extraction).

 @Autowired
 private UserDetailsServiceImpl userDetailsService;  // Service to load user details by username.

 @Override
 // This method is called for every request and handles the logic of validating the JWT token and setting the authentication context.
 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
         throws ServletException, IOException {
     // Extract the "Authorization" header from the request.
     String authorizationHeader = request.getHeader("Authorization");
     String token = null;
     String username = null;

     // Check if the Authorization header contains a Bearer token.
     if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
         token = authorizationHeader.substring(7);  // Extract the token by removing the "Bearer " prefix.
         username = jwtService.extractUsername(token);  // Extract the username from the token using the JwtService.
     }

     // If a username is present and the user is not already authenticated:
     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
         // Load the user details using the username extracted from the token.
         UserDetails userDetails = userDetailsService.loadUserByUsername(username);

         // Validate the token against the user details.
         if (jwtService.isTokenValid(token, userDetails)) {
             // If the token is valid, create an authentication token for the user.
             UsernamePasswordAuthenticationToken authToken = 
                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
             // Set additional details from the web request (e.g., IP address, session ID).
             authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             // Set the authenticated user in the security context.
             SecurityContextHolder.getContext().setAuthentication(authToken);
         }
     }

     // Continue the filter chain, allowing other filters to process the request.
     filterChain.doFilter(request, response);
 }
}
