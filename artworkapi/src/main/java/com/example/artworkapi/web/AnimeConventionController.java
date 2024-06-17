package com.example.artworkapi.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.artworkapi.domain.AnimeConventionArt;
import com.example.artworkapi.model.AnimeConventionRepository;

//Indicates that this class is a REST controller, handling HTTP requests and returning JSON responses
@RestController
//Enables Cross-Origin Resource Sharing (CORS) for this controller, allowing requests from the specified origin
@CrossOrigin("https://react-carsandboats.onrender.com/")
public class AnimeConventionController {
	
	 // Dependency injection of the AnimeConventionRepository
	private final AnimeConventionRepository repository;
  
	// Constructor for dependency injection of the repository
	public AnimeConventionController(AnimeConventionRepository repository) {
	    this.repository = repository;
	}
  
	// Handles HTTP GET requests to the /animeConvention endpoint
    @GetMapping("/animeConvention")
    // Method to get all anime convention artworks
	public Iterable<AnimeConventionArt> getAnimeConvention(){
    	// Retrieves and returns all AnimeConventionArt entities from the repository
    	return repository.findAll();
	}
  
}