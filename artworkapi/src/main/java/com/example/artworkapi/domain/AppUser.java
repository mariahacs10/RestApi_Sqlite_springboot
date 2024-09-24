package com.example.artworkapi.domain;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//Marks this class as a JPA entity, so it will be mapped to a database table
@Entity
public class AppUser {
 
 // Marks this field as the primary key of the entity
 @Id
 // Specifies that the primary key value will be generated automatically using the AUTO strategy
 @GeneratedValue(strategy = GenerationType.AUTO)
 // Specifies that this column is not nullable and cannot be updated
 @Column(nullable = false, updatable = false)
 private Long id;
 
 // Specifies that this column is not nullable and must be unique
 @Column(nullable = false, unique = true)
 private String username;
 
 // Specifies that this column is not nullable
 @Column(nullable = false)
 private String password;
 
 @Column(nullable = false, unique = true)
 private String email;
 
 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
 private Set<Favorite> favorites = new HashSet<>();
 
 @Column(nullable = true)
 private String googleId;
 
 @Column(nullable = true)
 private String firstName;
 
 @Column(nullable = false)
 private String role;
 
 @Column(nullable = true)
 private String passwordResetToken;

 @Column(nullable = true)
 private LocalDateTime passwordResetTokenExpiration;

 // Default constructor required by JPA
 public AppUser() {
 }
 
 // Constructor to initialize the entity with username, password, and role
 public AppUser(String username, String password, String email,  String role) {
     this.username = username;
     this.password = password;
     this.email = email;
     this.role = role;
     this.firstName = "";
     this.googleId = "";
 }
 
 public String getPasswordResetToken() {
     return passwordResetToken;
 }

 public void setPasswordResetToken(String passwordResetToken) {
     this.passwordResetToken = passwordResetToken;
 }

 public LocalDateTime getPasswordResetTokenExpiration() {
     return passwordResetTokenExpiration;
 }

 public void setPasswordResetTokenExpiration(LocalDateTime passwordResetTokenExpiration) {
     this.passwordResetTokenExpiration = passwordResetTokenExpiration;
 }
 
 public String getEmail() {
     return email;
 }
 
 public void setEmail(String email) {
     this.email = email;
 }

 
 public String getFirstName()
 {
	 return firstName;
 }
 
 public void setFirstName(String firstName)
 {
	 this.firstName = firstName;
 }

 
 
 public String getRole() {
	    return role;
	}

	public void setRole(String role) {
	    this.role = role;
	}
 
 // Getter method for the primary key field
 public Long getId() {
     return id;
 }
 
 // Setter method for the primary key field
 public void setId(Long id) {
     this.id = id;
 }
 
//Getter method for the primary key field
public String getGoogleId() {
   return googleId;
}

// Setter method for the primary key field
public void setGoogleId(String googleId) {
   this.googleId = googleId;
}
 
 // Getter method for the username field
 public String getUsername() {
     return username;
 }
 
 // Setter method for the username field
 public void setUsername(String username) {
     this.username = username;
 }
 
 // Getter method for the password field
 public String getPassword() {
     return password;
 }
 
 // Setter method for the password field
 public void setPassword(String password) {
     this.password = password;
 }
 
 // New methods for managing favorites
 public Set<Favorite> getFavorites() {
     return favorites;
 }

 public void setFavorites(Set<Favorite> favorites) {
     this.favorites = favorites;
 }

 public void addFavorite(Favorite favorite) {
     favorites.add(favorite);
     favorite.setUser(this);
 }

 public void removeFavorite(Favorite favorite) {
     favorites.remove(favorite);
     favorite.setUser(null);
 }
 
}
