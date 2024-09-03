package com.example.artworkapi.domain;

//This class represents a signup request, containing username and password
public class SignupRequest {

 // Private fields to hold username, email and password
 private String username;
 private String password;
 private String email;

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
 
 public String getEmail() {
     return email;
 }

 public void setEmail(String email) {
     this.email = email;
 }
}
