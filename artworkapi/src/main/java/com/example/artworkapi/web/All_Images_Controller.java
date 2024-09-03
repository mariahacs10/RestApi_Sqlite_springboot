package com.example.artworkapi.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.artworkapi.domain.All_Images;
import com.example.artworkapi.model.All_Images_Repository;

//Indicates that this class is a REST controller, handling HTTP requests and returning JSON responses
@RestController
//Enables Cross-Origin Resource Sharing (CORS) for this controller, allowing requests from the specified origin
@CrossOrigin("https://react-carsandboats.onrender.com/")
public class All_Images_Controller {
	
	 // Dependency injection of the AnimeConventionRepository
	private final All_Images_Repository repository;
  
	// Constructor for dependency injection of the repository
	public All_Images_Controller(All_Images_Repository repository) {
	    this.repository = repository;
	}
  
	// Handles HTTP GET requests to the /animeConvention endpoint
    @GetMapping("/allImages")
    // Method to get all anime convention artworks
	public Iterable<All_Images> getAllImages(){
    	// Retrieves and returns all AnimeConventionArt entities from the repository
    	return repository.findAll();
	}
	
	  @GetMapping("/allImages/category/{category}")
	    public List<All_Images> getImagesByCategory(@PathVariable String category) {
	        return repository.findByCategory(category);
	    }
}