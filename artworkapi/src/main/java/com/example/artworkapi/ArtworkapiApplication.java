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

/* Important notes on why we are not hardcoding url links:
  
  Embedding URLs or any hard-coded data directly in the main or CommandLineRunner class is not a 
  good practice because it makes the code less modular, harder to maintain, and difficult to scale. 
  
  side note:(If your Spring Boot application already uses application.properties, you don't have to create an 
  application.yml file. You can continue using application.properties and adapt it for structured data like URLs.)
 
   And remember you do not need to delete anything in the spring boot database
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 
 	QUESTIONS THAT MAY COME UP AGAIN!:
 
  Question:
   What is the purpose for ImageUrlsConfig when i have an AllImages entity class that
   has the allImageurls and etc.
   
  Answer:
    The ImageUrlsConfig class is used only during application configuration to load data from your
    application.properties file. This class serves as a bridge between the external configuration 
    (application.properties) and your application logic. It has nothing to do with your database or the
    AllImages entity itself.
    
    
   Question: 
     In the ImageUrlsConfig, do i not need to setup category?
     because we have category implemented in the static class imagedetail:
   
   Answer:
     You do not need to set up category explicitly at the top level of the ImageUrlsConfig class because it's 
     already part of the ImageDetail inner class
    
  
*/

@SpringBootApplication
public class ArtworkapiApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ArtworkapiApplication.class);

    private final All_Images_Repository allImagesRepository;
    private final ImageUrlsConfig imageUrlsConfig;
    private final ApiKeyService apiKeyService;

    public ArtworkapiApplication(All_Images_Repository allImagesRepository, ImageUrlsConfig imageUrlsConfig, ApiKeyService apiKeyService) {
        this.allImagesRepository = allImagesRepository;
        this.imageUrlsConfig = imageUrlsConfig;
        this.apiKeyService = apiKeyService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ArtworkapiApplication.class, args);
    }

    @PostConstruct
    public void initApiKeys() {
        String apiKey = apiKeyService.generateApiKey();
        System.out.println("Generated API key: " + apiKey);
    }

    @Override
    public void run(String... args) {
        // Save anime images
        imageUrlsConfig.getAnime().forEach(image -> saveImageIfNotExists(
                image.getUrl(), image.getDescription(), image.getCategory()));

        // Save JJK images
        imageUrlsConfig.getJjk().forEach(image -> saveImageIfNotExists(
                image.getUrl(), image.getDescription(), image.getCategory()));
    }

    private void saveImageIfNotExists(String imageUrl, String description, String category) {
        if (!allImagesRepository.findByAllImageUrl(imageUrl).isPresent()) {
            All_Images newImage = new All_Images(null, imageUrl, description, category);
            allImagesRepository.save(newImage);
        }
    }
}