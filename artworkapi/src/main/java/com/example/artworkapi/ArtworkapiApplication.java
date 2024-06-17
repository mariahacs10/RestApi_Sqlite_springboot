package com.example.artworkapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.artworkapi.domain.AnimeConventionArt;
import com.example.artworkapi.domain.AppUser;
import com.example.artworkapi.model.AnimeConventionRepository;
import com.example.artworkapi.model.AppUserRepository;
import com.example.artworkapi.service.ApiKeyService;

import jakarta.annotation.PostConstruct;

//indicates that this is a spring boot application
@SpringBootApplication
public class ArtworkapiApplication implements CommandLineRunner
{
	// Logger for logging messages, useful for debugging and monitoring the application
	private static final Logger logger = 
			LoggerFactory.getLogger(ArtworkapiApplication.class);

	 // Repository for accessing anime convention artwork data
	private final AnimeConventionRepository animeRepository;
	
	 // Uncomment if you have another repository for Erika's artwork
	//private final ErikasArtWorkRepository artWorkRepository;
	
	// Service for managing API keys
    private final ApiKeyService apiKeyService;
    
    // Constructor for dependency injection of the repositories and service
	public ArtworkapiApplication(AnimeConventionRepository animeRepository, ApiKeyService apiKeyService) {
		this.animeRepository = animeRepository;
		//this.artWorkRepository = artWorkRepository;
		this.apiKeyService = apiKeyService;
	}

	// Main method to launch the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(ArtworkapiApplication.class, args);
	}
	
    // Method annotated with @PostConstruct to execute after the bean initialization
	@PostConstruct
	 public void initApiKeys() {
		  // Generate an API key using the ApiKeyService
	      String apiKey = apiKeyService.generateApiKey();
	      // Print the generated API key to the console
	      System.out.println("Generated  api key: " + apiKey);
	}

	// Overridden method from CommandLineRunner to execute code after application startup
	@Override
	public void run(String... args) throws Exception {
		
		// URLs of anime photos to be saved in the repository
		String animePhoto1 = "https://i.ibb.co/F89g6pB/animephoto1.jpg";
		String animePhoto2 = "https://i.ibb.co/Ln6621f/animephoto2.jpg";
		String animePhoto3 = "https://i.ibb.co/LJzwYrF/animephoto3.jpg";
		String animePhoto4 = "https://i.ibb.co/mc8VvqM/animephoto4.jpg";
		String animePhoto5 = "https://i.ibb.co/mJKHkCd/animephoto5.jpg";
		String animePhoto6 = "https://i.ibb.co/FX0nmnc/animephoto6.jpg";
		String animePhoto7 = "https://i.ibb.co/XWh3Syy/animephoto7.jpg";
		String animePhoto8 = "https://i.ibb.co/Cswm6BG/animephoto8.jpg";
		String animePhoto9 = "https://i.ibb.co/mFKcyRn/animephoto9.jpg";
		String animePhoto10 = "https://i.ibb.co/Nn3bGZx/animephoto10.jpg";
		String animePhoto11 = "https://i.ibb.co/stz5vwz/animephoto11.jpg";
		String animePhoto12 = "https://i.ibb.co/YPgvLqS/animephoto12.jpg";
		String animePhoto13 = "https://i.ibb.co/JxrGwsY/animephoto13.jpg";
		String animePhoto14 = "https://i.ibb.co/XYPPCxL/animephoto14.jpg";
		String animePhoto15 = "https://i.ibb.co/5rKp2bb/animephoto15.jpg";
		String animePhoto16 = "https://i.ibb.co/XxMyc2f/animephoto16.jpg";
		String animePhoto17 = "https://i.ibb.co/4Jw3WmQ/animephoto17.jpg";
		String animePhoto18 = "https://i.ibb.co/JKfBzRY/animephoto18.jpg";
		String animePhoto19 = "https://i.ibb.co/y8S82w1/animephoto19.jpg";
		String animePhoto20 = "https://i.ibb.co/Q62byf8/animephoto20.jpg";
		
		// Save the anime photos along with their descriptions into the repository
		animeRepository.save(new AnimeConventionArt(animePhoto1, "aaron goofy pic"));
		animeRepository.save(new AnimeConventionArt(animePhoto2, "Group Photo 1"));
		animeRepository.save(new AnimeConventionArt(animePhoto3, "Photo With Mariah and Erika"));
		animeRepository.save(new AnimeConventionArt(animePhoto4, "Gojo Pic with Erika"));
		animeRepository.save(new AnimeConventionArt(animePhoto5, "Mahito Pic with Erika Pose 1"));
		animeRepository.save(new AnimeConventionArt(animePhoto6, "Mahito Pic with Erika Pose 2"));
		animeRepository.save(new AnimeConventionArt(animePhoto7, "Gojo Solo Pic"));
		animeRepository.save(new AnimeConventionArt(animePhoto8, "Toji picture pose 1"));
		animeRepository.save(new AnimeConventionArt(animePhoto9, "Riko Picture pose 1"));
		animeRepository.save(new AnimeConventionArt(animePhoto10, "Riko Picture pose 2"));
		animeRepository.save(new AnimeConventionArt(animePhoto11, "Toji picture pose 2"));
		animeRepository.save(new AnimeConventionArt(animePhoto12, "Nanimi picture with Erika"));
		animeRepository.save(new AnimeConventionArt(animePhoto13, "Inumaki picture with Mariah/Me pose 1"));
		animeRepository.save(new AnimeConventionArt(animePhoto14, "Inumaki picture with Mariah/Me pose 2"));
		animeRepository.save(new AnimeConventionArt(animePhoto15, "Mommy and Erika picture"));
		animeRepository.save(new AnimeConventionArt(animePhoto16, "Uraume picture with Mariah and Erika"));
		animeRepository.save(new AnimeConventionArt(animePhoto17, "Choso, Toji group photo"));
		animeRepository.save(new AnimeConventionArt(animePhoto18, "Geto and Geto photo :)"));
		animeRepository.save(new AnimeConventionArt(animePhoto19, "Group Photo 2"));
		animeRepository.save(new AnimeConventionArt(animePhoto20, "Random person picture, Mariah and Erika"));	

	}
}
