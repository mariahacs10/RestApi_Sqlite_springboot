package com.example.artworkapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Indicates that this class is a configuration class for Spring
@Configuration
//Implements WebMvcConfigurer to customize the Spring MVC configuration
public class WebConfig implements WebMvcConfigurer {

	// Overrides the addCorsMappings method to define custom CORS settings
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	// Adds CORS mappings for all endpoints
        registry.addMapping("/**") 
                // Allows requests from the specified origin
        		.allowedOrigins("https://react-carsandboats.onrender.com/")
        		// Allows the specified HTTP methods
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // Allows all headers
                .allowedHeaders("*");
    }
}