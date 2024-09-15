package com.example.artworkapi.domain;

//This class represents a request object for resetting a password
public class ResetPasswordRequest {
 // The token used to authenticate the password reset request
 private String token;
 
 // The new password that the user wants to set
 private String newPassword;
 
 private String confirmPassword;

 // Default constructor
 // This allows creating an instance without initializing fields
 public ResetPasswordRequest() {
 }

 // Parameterized constructor
 // This allows creating an instance with all fields initialized
 public ResetPasswordRequest(String token, String newPassword, String confirmPassword) {
     this.token = token;
     this.newPassword = newPassword;
 }
 
 public String getConfirmPassword()
 {
	 return confirmPassword;
 }
 
 public void setConfirmPassword(String confirmPassword)
 {
	 this.confirmPassword = confirmPassword;
 }

 // Getter for token
 // This method allows retrieving the value of the token field
 public String getToken() {
     return token;
 }

 // Setter for token
 // This method allows setting or updating the value of the token field
 public void setToken(String token) {
     this.token = token;
 }

 // Getter for newPassword
 // This method allows retrieving the value of the newPassword field
 public String getNewPassword() {
     return newPassword;
 }

 // Setter for newPassword
 // This method allows setting or updating the value of the newPassword field
 public void setNewPassword(String newPassword) {
     this.newPassword = newPassword;
 }
}