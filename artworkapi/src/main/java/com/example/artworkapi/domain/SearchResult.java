package com.example.artworkapi.domain;

//Define a public class named SearchResult
public class SearchResult {
 
 // Private instance variables for storing search result information
 private String type;          // Type of the search result
 private Long id;              // Unique identifier of the search result
 private String imageUrl;      // URL of an image associated with the search result
 private String description;   // Description or details of the search result

 // Default constructor (no arguments)
 public SearchResult() {
 }

 // Constructor with parameters to initialize all fields
 public SearchResult(String type, Long id, String imageUrl, String description) {
     this.type = type;               // Initialize type
     this.id = id;                   // Initialize id
     this.imageUrl = imageUrl;       // Initialize imageUrl
     this.description = description; // Initialize description
 }

 // Getter method for retrieving the type of the search result
 public String getType() {
     return type;
 }

 // Getter method for retrieving the id of the search result
 public Long getId() {
     return id;
 }

 // Getter method for retrieving the image URL of the search result
 public String getImageUrl() {
     return imageUrl;
 }

 // Getter method for retrieving the description of the search result
 public String getDescription() {
     return description;
 }

 // Setter method to set the type of the search result
 public void setType(String type) {
     this.type = type;
 }

 // Setter method to set the id of the search result
 public void setId(Long id) {
     this.id = id;
 }

 // Setter method to set the image URL of the search result
 public void setImageUrl(String imageUrl) {
     this.imageUrl = imageUrl;
 }

 // Setter method to set the description of the search result
 public void setDescription(String description) {
     this.description = description;
 }

 // Optional: Override toString() method to provide a string representation of the object
 @Override
 public String toString() {
     return "SearchResult{" +
             "type='" + type + '\'' +
             ", id=" + id +
             ", imageUrl='" + imageUrl + '\'' +
             ", description='" + description + '\'' +
             '}';
 }
}
