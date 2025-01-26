package com.example.artworkapi;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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

import com.example.artworkapi.service.JwtService;
import com.example.artworkapi.service.UserDetailsServiceImpl;

//Indicates that this class is a configuration class for Spring
@Configuration
@EnableWebSecurity
// This class is responsible for configuring security settings in the Spring Boot application.
// It uses Spring Security for managing authentication and authorization.

public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ApiKeyFilter apiKeyFilter;

    // Constructor-based dependency injection for various services and filters.
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtService jwtService, JwtAuthenticationFilter jwtAuthenticationFilter, ApiKeyFilter apiKeyFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.apiKeyFilter = apiKeyFilter;
    }

    @Bean
    // Bean for password encoding, using BCryptPasswordEncoder for hashing passwords securely.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // Bean for configuring the authentication manager, which handles user authentication.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @SuppressWarnings("deprecation")
    @Bean
    // SecurityFilterChain defines the security filters and rules for HTTP requests.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF protection as it's not needed for stateless APIs.
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Use stateless session management (no session creation).
            .authorizeRequests(authorizeRequests -> authorizeRequests
            		.requestMatchers("/api/auth/**").permitAll()  // Allow unauthenticated access to authentication-related endpoints.
                    .requestMatchers("/api/auth/forgot-password").permitAll()  // Public access to password reset.
                    .requestMatchers("/api/auth/signup").permitAll()  // Public access to signup.
                    .requestMatchers("/reset-password").permitAll()  // Public access to reset password.
                    .requestMatchers("/api/auth/reset-password").permitAll()  // Public access to reset password.
                    .requestMatchers("/api/auth/login").permitAll()  // Public access to login.
                    .requestMatchers("/api/auth/google-signin").permitAll()  // Public access to Google Sign-in.
                    .requestMatchers("/allImages/**").hasAuthority("apiKey")  // Requires API key for accessing allImages endpoints.
                    .requestMatchers("/allImages/category/{category}").hasAuthority("apiKey")  // Requires API key for category-specific images.
                    .requestMatchers("/favorites/addFav").hasAuthority("apiKey")  // Requires API key to add favorites.
                    .requestMatchers("/favorites/user/**").hasAuthority("apiKey")  // Requires API key to view user favorites.
                    .requestMatchers("/favorites/delete/**").hasAuthority("apiKey")  // Requires API key to delete favorites.
                    .anyRequest().authenticated())  // All other requests require authentication.
            .addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class)  // Add the API key filter before the JWT filter.
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // Add the JWT authentication filter.
            .cors(withDefaults());  // Enable CORS with default configuration.
        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

