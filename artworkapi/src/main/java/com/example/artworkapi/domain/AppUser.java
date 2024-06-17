package com.example.artworkapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
 
 // Specifies that this column is not nullable
 @Column(nullable = false)
 private String role;
 
 // Default constructor required by JPA
 public AppUser() {
 }
 
 // Constructor to initialize the entity with username, password, and role
 public AppUser(String username, String password, String role) {
     this.username = username;
     this.password = password;
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
 
 // Getter method for the role field
 public String getRole() {
     return role;
 }
 
 // Setter method for the role field
 public void setRole(String role) {
     this.role = role;
 }
}
