package com.example.artworkapi;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.artworkapi.service.ApiKeyService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//This class is a filter that checks for an API key in the request header
@Component 
// This annotation is commented out for demonstration purposes
public class ApiKeyFilter extends OncePerRequestFilter {

	 private final ApiKeyService apiKeyService;
	 private static final Logger log = LoggerFactory.getLogger(ApiKeyFilter.class);
	
	 public ApiKeyFilter(ApiKeyService apiKeyService) {
	     this.apiKeyService = apiKeyService;
	 }
	
	 // This method performs the actual filtering logic on each request
	 @Override
	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	         throws ServletException, IOException {
	
	     // This line retrieves the API key from the request header named "X-Api-Key"
	     String apiKey = request.getHeader("X-Api-Key");
	     log.info("Received API key: {}", apiKey);
	
	     // Checks if the API key is present and valid using the ApiKeyService
	     if (apiKey == null || (apiKey != null && apiKeyService.validateApiKey(apiKey))) {
	         //  sets the authentication context with the API key and a granted authority named "apiKey"
	         setAuthentication(apiKey, "apiKey");
	         filterChain.doFilter(request, response); // Allows the request to proceed if valid
	     } else {
	         log.debug("Invalid API key or missing API key.");
	         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	         response.getWriter().write("Invalid API key.");
	     }
	 }
	
	 // It sets the authentication context with the provided API key and authority
	 private void setAuthentication(String apiKey, String authority) {
	     UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(apiKey, null, 
	         Collections.singletonList(new SimpleGrantedAuthority(authority)));
	     SecurityContextHolder.getContext().setAuthentication(authentication);
	 }
}

