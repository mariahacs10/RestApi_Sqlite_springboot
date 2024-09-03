package com.example.artworkapi.service;


import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {
	
	public static final String ANIME_API_KEY = System.getenv("ANIMECON_API_KEY");

	
	//This is the method to generate the api key
	public String generateApiKey() {
        return ANIME_API_KEY;
    }
	
   
    //this is the method to validate the api key
    public boolean validateApiKey(String apiKey) {
        return ANIME_API_KEY.contains(apiKey);
    }
    
}