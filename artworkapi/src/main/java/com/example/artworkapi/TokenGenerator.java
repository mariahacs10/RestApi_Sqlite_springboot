package com.example.artworkapi;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.boot.SpringApplication;

//This class is responsible for generating secure tokens
public class TokenGenerator {

 // This method generates a secure random token
 public static String generateToken() {
     // Create a new instance of SecureRandom for cryptographically strong random number generation
     SecureRandom secureRandom = new SecureRandom();
     
     // Create a byte array to store the random bytes
     byte[] tokenBytes = new byte[24];
     
     // Fill the byte array with random bytes
     secureRandom.nextBytes(tokenBytes);
     
     // Convert the random bytes to a URL-safe Base64 encoded string without padding
     return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
 }

	 // Main method for testing the token generation
	 public static void main(String[] args) {
	     // Print a generated token to the console
	     System.out.println("Generated Token: " + generateToken());
	 }
}