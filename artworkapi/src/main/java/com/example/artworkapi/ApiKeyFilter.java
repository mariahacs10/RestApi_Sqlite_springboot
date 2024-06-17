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

//This declares the ApiKeyFilter class as a spring component,
//which means it will be managed by the Spring container
@Component
//The class extends OncePerRequestFilter, which is a base class provided by
//spring Security for implementing filters that should execute once per request
public class ApiKeyFilter extends OncePerRequestFilter {
	//This line declares a private final instance variable of type
	//ApiKeyService. It will be used to access the methods for generating
	//and validating apikeys
  private final ApiKeyService apiKeyService;

  //This line creates a static logger instance using the LoggerFactory from
  //the SLF4J logging framework. the logger will be used to log messages
  //related to the ApiKeyFilter class
  private static final Logger log = (Logger) LoggerFactory.getLogger(ApiKeyFilter.class);
  //This line declares a constant APIKEYPARAM that holds the name of the request
  //parameter used to pass the apikey

  public ApiKeyFilter(ApiKeyService apiKeyService) {
      this.apiKeyService = apiKeyService;
  }

  //This method overrides the doFilterInternal method from the 
  //OncePerRequestFilter class. It is the Main method that will be executed
  //for each incomming request
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
  	//To use the X-Api-Key header instead of a query parameter, you need to make changes in both your client code (where you send the request) and the server-side code (where you receive and validate the API key).
  	//On the client-side (e.g., Postman or your frontend application), you need to set the X-Api-Key header with the appropriate value instead of sending it as a query parameter.
  	//On the server-side, you need to modify the ApiKeyFilter class to read the API key from the X-Api-Key header instead of the request parameter
  	
  	//In this modified code, we're using request.getHeader("X-Api-Key") to retrieve the value of the X-Api-Key header instead of request.getParameter(API_KEY_PARAM).
     //This is the only line you need to change!
  	String apiKey = request.getHeader("X-Api-Key");
      log.info("Received API key: {}", apiKey);

      if (apiKey != null && !apiKey.isEmpty()) {
          if (isAnimeConRequest(request)) {
              if (apiKeyService.validateApiKey(apiKey)) {
                  setAuthentication(apiKey);
                  filterChain.doFilter(request, response);
                  return;
              }
          }  else {
              log.info("Failed");
          }
       }

      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
     response.getWriter().write("Invalid API key.");
  }

  //This is a private helper method that checks if the incoming request is for the
  //animeConvention entity by examining the request URL. If the URL starts
  //with "/animeConvention", it returns true; otherwise, it returns false.
  private boolean isAnimeConRequest(HttpServletRequest request) {
      String requestUrl = request.getRequestURI();
      return requestUrl.startsWith("/animeConvention");
  }

  private void setAuthentication(String apiKey) {
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(apiKey, null, Collections.singletonList(new SimpleGrantedAuthority("apiKey")));
      SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}