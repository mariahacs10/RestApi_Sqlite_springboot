package com.example.artworkapi.domain;

public class LoginRequest {

  // Private fields to hold username and password
	 private String username;
	 private String password;

	 // Getter method to retrieve the username
	 public String getUsername() {
	     return username;
	 }

	 // Setter method to set the username
	 public void setUsername(String username) {
	     this.username = username;
	 }

	 // Getter method to retrieve the password
	 public String getPassword() {
	     return password;
	 }

	 // Setter method to set the password
	 public void setPassword(String password) {
	     this.password = password;
	 }
}
