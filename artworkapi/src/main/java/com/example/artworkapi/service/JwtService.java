package com.example.artworkapi.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.artworkapi.domain.AppUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
//The JwtService class is responsible for generating, validating, and extracting information from JWT tokens.
//It uses a static secret key to sign the JWT tokens and provides methods for handling JWT-related operations.

public class JwtService {

 private static final String SECRET_KEY = "er-W1rwldgFOx-4hQlz4WqdDkarU56PCACBHgG3CIrA";
 // The SECRET_KEY is a static string used for signing and verifying JWT tokens.
 // Ensure that the key is kept secure and private, as it is critical to token security.

 // Method to generate a JWT token for a given AppUser.
 public String generateToken(AppUser user) {
     // Claims can hold additional metadata about the token. Here, an empty map is used for simplicity.
     Map<String, Object> claims = new HashMap<>();
     return createToken(claims, user.getUsername()); // Generate the token using the username as the subject.
 }

 // Private method to create a JWT token with the provided claims and subject (username).
 private String createToken(Map<String, Object> claims, String subject) {
     long tenYearsInMillis = 10L * 365 * 24 * 60 * 60 * 1000;  // Define a token expiration time of 10 years.
     Date expirationDate = new Date(System.currentTimeMillis() + tenYearsInMillis);  // Calculate the expiration date.

     // Use the Jwts builder to create the token with claims, subject, issue date, expiration date, and sign it with the secret key.
     return Jwts.builder()
             .setClaims(claims)
             .setSubject(subject)
             .setIssuedAt(new Date(System.currentTimeMillis()))  // Set the current time as the issued date.
             .setExpiration(expirationDate)  // Set the expiration date.
             .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Sign the token with HS256 and the secret key.
             .compact();  // Compact the token into a string format.
 }

 // Method to extract the username (subject) from the JWT token.
 public String extractUsername(String token) {
     return extractClaim(token, Claims::getSubject);  // Extract the subject (username) from the token claims.
 }

 // Generic method to extract a specific claim from the JWT token, using a resolver function.
 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
     final Claims claims = extractAllClaims(token);  // Extract all claims from the token.
     return claimsResolver.apply(claims);  // Apply the resolver function to the claims.
 }

 // Private method to extract all claims from a JWT token using the secret key.
 private Claims extractAllClaims(String token) {
     return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();  // Parse the token and extract the claims body.
 }

 // Method to validate the token by checking if the username matches and the token is not expired.
 public Boolean isTokenValid(String token, UserDetails userDetails) {
     final String username = extractUsername(token);  // Extract the username from the token.
     return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));  // Check if the username matches and the token is not expired.
 }

 // Private method to check if the token is expired.
 private Boolean isTokenExpired(String token) {
     return extractExpiration(token).before(new Date());  // Compare the token expiration date with the current date.
 }

 // Private method to extract the expiration date from the token.
 private Date extractExpiration(String token) {
     return extractClaim(token, Claims::getExpiration);  // Extract the expiration date from the token claims.
 }
}
