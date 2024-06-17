package com.example.artworkapi.service;


import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {

	//This is the method to generate the api key
	public String generateApiKey() {
        return ApiKeys.ANIMECON_API_KEY;
    }
   
    //this is the method to validate the api key
    public boolean validateApiKey(String apiKey) {
        return ApiKeys.ANIMECON_API_KEY.contains(apiKey);
    }
}