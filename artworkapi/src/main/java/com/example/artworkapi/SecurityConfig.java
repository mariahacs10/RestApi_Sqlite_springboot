package com.example.artworkapi;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.
 EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.example.artworkapi.service.UserDetailsServiceImpl;

//Indicates that this class is a configuration class for Spring
@Configuration
//Enables Spring Security for the application
@EnableWebSecurity
public class SecurityConfig {
	
     // Service to load user-specific data
	 private final UserDetailsServiceImpl userDetailsService;
	
	 // Custom filter to handle API key authentication
	 private final ApiKeyFilter apiKeyFilter;
	 
	 // Constructor for dependency injection of UserDetailsServiceImpl and ApiKeyFilter
	 public SecurityConfig(UserDetailsServiceImpl userDetailsService, ApiKeyFilter apiKeyFilter) {
			  this.userDetailsService = userDetailsService;
		      this.apiKeyFilter = apiKeyFilter;
	 }
	 
	 // Configures global authentication with user details service and password encoder
	 public void configureGlobal (AuthenticationManagerBuilder auth)
		throws Exception {
		     auth.userDetailsService(userDetailsService)
		     .passwordEncoder(new BCryptPasswordEncoder());
	 }
	 
	 // Defines a bean for the password encoder, using BCrypt
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	     return new BCryptPasswordEncoder();
	 }
	 
	// Defines a bean for the authentication manager
	 @Bean
	 public AuthenticationManager authenticationManager(
	   AuthenticationConfiguration authConfig) throws Exception {
	     return authConfig.getAuthenticationManager();
	 }
	
	 // Defines the security filter chain
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 // Disables CSRF protection
	     http.csrf((csrf) -> csrf.disable())
	         // Configures session management to be stateless
	         .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	         // Adds custom API key filter after the username/password authentication filter
	         .addFilterAfter(apiKeyFilter, UsernamePasswordAuthenticationFilter.class)
	         // Configures authorization rules
	         .authorizeHttpRequests((authorizeHttpRequests) ->
	             authorizeHttpRequests
	             	 // Requires 'apiKey' authority for endpoints under /animeConvention/**
	                 .requestMatchers("/animeConvention/**").hasAuthority("apiKey")
	                 .requestMatchers("/erikasArtWork/**").hasAnyAuthority("apiKey")
	                 .requestMatchers("/api/search/**").hasAnyAuthority("animeConventionApiKey", "erikasArtWorkApiKey")
	                 // All other requests require authentication
	                 .anyRequest().authenticated())
	       // Enables CORS with default settings
	     .cors(withDefaults());
	
	     // Builds the security filter chain
	     return http.build();
	 }
	 
	 
	 
	// Defines a bean for CORS configuration source
	 @Bean
	 public CorsConfigurationSource corsConfigurationSource() {
		    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		    CorsConfiguration config = new CorsConfiguration();
		    // Allows all origins
		    config.setAllowedOrigins(Arrays.asList("*"));
		    // Allows all HTTP methods
		    config.setAllowedMethods(Arrays.asList("*"));
		    // Allows all headers
		    config.setAllowedHeaders(Arrays.asList("*"));
		    // Does not allow credentials
		    config.setAllowCredentials(false);
		    // Applies default values
		    config.applyPermitDefaultValues();
		    
		    // Registers the CORS configuration for all endpoints
		    source.registerCorsConfiguration("/**", config);
		    return source;
	 }
}