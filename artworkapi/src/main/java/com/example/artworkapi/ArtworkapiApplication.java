package com.example.artworkapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.artworkapi.domain.All_Images;
import com.example.artworkapi.model.All_Images_Repository;
import com.example.artworkapi.service.ApiKeyService;

import jakarta.annotation.PostConstruct;

//indicates that this is a spring boot application
@SpringBootApplication
public class ArtworkapiApplication implements CommandLineRunner
{
	/** Changes i've made in my app
	  
	  1.I deleted the search feature
	  
	  2.I deleted 1 table and renamed the other table to AllImages
	  
	  3.Modified the Repositories and Controllers
	  
	  4.Changed/Modified the SecurityConfig
	  
	  5.Changed the ApiKey logic, also remove the other api key.
	  
	  REMEMBER to use api/auth/resetpassword and not just /resetpassword
	*/
	
	// Logger for logging messages, useful for debugging and monitoring the application
	private static final Logger logger = 
			LoggerFactory.getLogger(ArtworkapiApplication.class);

	 // Repository for accessing anime convention artwork data
	private final All_Images_Repository allImagesRepository;
	
	
	
	// Service for managing API keys
    private final ApiKeyService apiKeyService;
    
    // Constructor for dependency injection of the repositories and service
	public ArtworkapiApplication(All_Images_Repository allImagesRepository, ApiKeyService apiKeyService) {
		this.allImagesRepository = allImagesRepository;
		this.apiKeyService = apiKeyService;
	}

	// Main method to launch the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(ArtworkapiApplication.class, args);
	}
	
    // Method annotated with @PostConstruct to execute after the bean initialization
	@PostConstruct
	  public void initApiKeys() {
		      
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
		
	    String animeCategory = "anime";
	    String jjkCategory = "jjk";
	
	    
		// Save the anime photos along with their descriptions into the repository
	    saveImageIfNotExists(animePhoto1, "aaron goofy pic anime", animeCategory);
	    saveImageIfNotExists(animePhoto2, "Group Photo 1 anime", animeCategory);
	    saveImageIfNotExists(animePhoto3, "Photo With Mariah and Erika anime", animeCategory);
	    saveImageIfNotExists(animePhoto4, "Gojo Pic with Erika anime", animeCategory);
	    saveImageIfNotExists(animePhoto5, "Mahito Pic with Erika Pose 1 anime", animeCategory);
	    saveImageIfNotExists(animePhoto6, "Mahito Pic with Erika Pose 2 anime", animeCategory);
	    saveImageIfNotExists(animePhoto7, "Gojo Solo Pic anime", animeCategory);
	    saveImageIfNotExists(animePhoto8, "Toji picture pose 1 anime", animeCategory);
	    saveImageIfNotExists(animePhoto9, "Riko Picture pose 1 anime",animeCategory);
	    saveImageIfNotExists(animePhoto10, "Riko Picture pose 2 anime", animeCategory);
	    saveImageIfNotExists(animePhoto11, "Toji picture pose 2 anime", animeCategory);
	    saveImageIfNotExists(animePhoto12, "Nanimi picture with Erika anime", animeCategory);
	    saveImageIfNotExists(animePhoto13, "Inumaki picture with Mariah/Me pose 1 anime", animeCategory);
	    saveImageIfNotExists(animePhoto14, "Inumaki picture with Mariah/Me pose 2 anime", animeCategory);
	    saveImageIfNotExists(animePhoto15, "Mommy and Erika picture anime", animeCategory);
	    saveImageIfNotExists(animePhoto16, "Uraume picture with Mariah and Erika anime", animeCategory);
	    saveImageIfNotExists(animePhoto17, "Choso, Toji group photo anime", animeCategory);
	    saveImageIfNotExists(animePhoto18, "Geto and Geto photo :) anime", animeCategory);
	    saveImageIfNotExists(animePhoto19, "Group Photo 2 anime", animeCategory);
	    saveImageIfNotExists(animePhoto20, "Random person picture, Mariah and Erika anime", animeCategory);	

	    saveImageIfNotExists(jjkImage1, "Gojo ArtWork", jjkCategory);
	    saveImageIfNotExists(jjkImage2, "Gojo ArtWork 2", jjkCategory);
	    saveImageIfNotExists(jjkImage3, "Megumi Sukuna ArtWork", jjkCategory);
	    saveImageIfNotExists(jjkImage4, "Megumi Sukuna ArtWork 2", jjkCategory);
	    saveImageIfNotExists(jjkImage5, "Yuji ArtWork", jjkCategory);
	    saveImageIfNotExists(jjkImage6,"Yuji ArtWork 2", jjkCategory);
	    saveImageIfNotExists(jjkImage7, "Megumi Sukuna ArtWork 3", jjkCategory);
	    saveImageIfNotExists(jjkImage8, "Yuji ArtWork 3", jjkCategory);
	    saveImageIfNotExists(jjkImage9,"Gojo ArtWork 3",jjkCategory);
	    saveImageIfNotExists(jjkImage10, "Megumi and Yuji ArtWork", jjkCategory);
	    saveImageIfNotExists(jjkImage11, "Megumi ArtWork",jjkCategory);
	    saveImageIfNotExists(jjkImage12, "Toji ArtWork", jjkCategory);
	    saveImageIfNotExists(jjkImage13, "Toji ArtWork 2",jjkCategory);
	       
		
		
	}
	
	private void saveImageIfNotExists(String imageUrl, String description, String category) {
	    if (!allImagesRepository.findByAllImageUrl(imageUrl).isPresent()) {
	        allImagesRepository.save(new All_Images(imageUrl, description, category));
	    }
	}
}


