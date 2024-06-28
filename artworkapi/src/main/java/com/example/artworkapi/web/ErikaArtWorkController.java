package com.example.artworkapi.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.artworkapi.domain.ErikasArtWork;
import com.example.artworkapi.model.ErikasArtWorkRepository;

@RestController
@CrossOrigin("https://react-carsandboats.onrender.com/")
public class ErikaArtWorkController {
	
	private final ErikasArtWorkRepository repository;
  
  public ErikaArtWorkController(ErikasArtWorkRepository repository) {
      this.repository = repository;
  }
  
 
  @GetMapping("/erikasArtWork")
	public Iterable<ErikasArtWork> getArtWork(){
		//fetch and return cars
      return repository.findAll();
	}
  
}