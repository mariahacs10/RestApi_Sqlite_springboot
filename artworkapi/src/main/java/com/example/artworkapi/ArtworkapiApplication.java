package com.example.artworkapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.artworkapi.domain.AnimeConventionArt;
import com.example.artworkapi.domain.ErikasArtWork;
import com.example.artworkapi.model.AnimeConventionRepository;
import com.example.artworkapi.model.ErikasArtWorkRepository;
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
	
	private final ErikasArtWorkRepository artWorkRepository;
	
	// Service for managing API keys
    private final ApiKeyService apiKeyService;
    
    // Constructor for dependency injection of the repositories and service
	public ArtworkapiApplication(AnimeConventionRepository animeRepository, ApiKeyService apiKeyService, ErikasArtWorkRepository artWorkRepository) {
		this.animeRepository = animeRepository;
		//this.artWorkRepository = artWorkRepository;
		this.apiKeyService = apiKeyService;
		this.artWorkRepository = artWorkRepository;
	}

	// Main method to launch the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(ArtworkapiApplication.class, args);
	}
	
    // Method annotated with @PostConstruct to execute after the bean initialization
	@PostConstruct
	  public void initApiKeys() {
		      String apiKey = apiKeyService.generateArtworkApiKey();
		      System.out.println("Generated ART api key: " + apiKey);
		      
		      String apiKey2 = apiKeyService.generateApiKey();
		      System.out.println("Generated API key: " + apiKey2);
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
		
		  
	    String jjkImage1 = "https://i.postimg.cc/3rtbd3v4/jjkimage1.jpg";
	    String jjkImage2 = "https://i.postimg.cc/MKX3Bg7s/jjkimage2.jpg";
	    String jjkImage3 = "https://i.postimg.cc/m2qXyVVT/jjkimage3.jpg";
	    String jjkImage4 = "https://i.postimg.cc/pLF0G81z/jjkimage4.jpg";
	    String jjkImage5 = "https://i.postimg.cc/g0bgfJf5/jjkimage5.jpg";
	    String jjkImage6 = "https://i.postimg.cc/NMgpjnGk/jjkimage6.jpg";
	    String jjkImage7 = "https://i.postimg.cc/3Rn94Zd5/jjkimage7.jpg";
	    String jjkImage8 = "https://i.postimg.cc/ZnnHryd9/jjkimage8.jpg";
	    String jjkImage9 = "https://i.postimg.cc/nLK3ZxxL/jjkimage9.jpg";
	    String jjkImage10 = "https://i.postimg.cc/X7ZxqdWm/jjkimage10.jpg";
	    String jjkImage11 = "https://i.postimg.cc/ZYsVJjG6/jjkimage11.jpg";
	    String jjkImage12 = "https://i.postimg.cc/1XdHM5Gv/jjkimage12.jpg";
	    String jjkImage13 = "https://i.postimg.cc/c4VhnC4H/jjkimage13.jpg";
		
		
		
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

		 artWorkRepository.save(new ErikasArtWork(jjkImage1));
	     artWorkRepository.save(new ErikasArtWork(jjkImage2));
	     artWorkRepository.save(new ErikasArtWork(jjkImage3));
	     artWorkRepository.save(new ErikasArtWork(jjkImage4));
	     artWorkRepository.save(new ErikasArtWork(jjkImage5));
	     artWorkRepository.save(new ErikasArtWork(jjkImage6));
	     artWorkRepository.save(new ErikasArtWork(jjkImage7));
	     artWorkRepository.save(new ErikasArtWork(jjkImage8));
	     artWorkRepository.save(new ErikasArtWork(jjkImage9));
	     artWorkRepository.save(new ErikasArtWork(jjkImage10));
	     artWorkRepository.save(new ErikasArtWork(jjkImage11));
	     artWorkRepository.save(new ErikasArtWork(jjkImage12));
	     artWorkRepository.save(new ErikasArtWork(jjkImage13));
	       
		
		
	}
}
