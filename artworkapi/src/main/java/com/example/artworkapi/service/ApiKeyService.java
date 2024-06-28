package com.example.artworkapi.service;


import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {
	
	public static final String ANIME_API_KEY = System.getenv("ANIMECON_API_KEY");

	   public static final String ART_API_KEY = System.getenv("ERIKA_ARTWORK_API_KEY");
	
	//This is the method to generate the api key
	public String generateApiKey() {
        return ANIME_API_KEY;
    }
	
	public String generateArtworkApiKey() {
        return ART_API_KEY;
    }

   
    //this is the method to validate the api key
    public boolean validateApiKey(String apiKey) {
        return ANIME_API_KEY.contains(apiKey);
    }
    
    public boolean validateArtworkApiKey(String apiKey) {
        return ART_API_KEY.contains(apiKey);
    }
}