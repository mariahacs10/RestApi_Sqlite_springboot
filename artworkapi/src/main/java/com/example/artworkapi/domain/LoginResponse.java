package com.example.artworkapi.domain;

//This class represents the response object for a successful login.
//It contains the JWT token and the userId associated with the logged-in user.

public class LoginResponse {
 private String token;  // The JWT token issued upon successful login.
 private Long userId;   // The unique ID of the user.

 // Constructor to initialize the token and userId.
 public LoginResponse(String token, Long userId) {
     this.token = token;
     this.userId = userId;
 }

 // Getter method for retrieving the JWT token.
 public String getToken() {
     return token;
 }

 // Setter method for updating the JWT token.
 public void setToken(String token) {
     this.token = token;
 }

 // Getter method for retrieving the user ID.
 public Long getUserId() {
     return userId;
 }

 // Setter method for updating the user ID.
 public void setUserId(Long userId) {
     this.userId = userId;
 }
}
