package com.example.artworkapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Marks this class as a JPA entity, so it will be mapped to a database table
@Entity
//Specifies the name of the table in the database that this entity will be mapped to
@Table(name="animeConventionArt")
public class AnimeConventionArt {

	 // Marks this field as the primary key of the entity
	@Id
	// Specifies that the primary key value will be generated automatically
	@GeneratedValue
	private Long animeConventionId;
	
	// Field to store the URL of the anime convention image
    private String animeConventionImageUrl; 
    
    // Field to store the description of the anime convention artwork
    private String animeConventionDescription;
	
    // Constructor to initialize the entity with an image URL and description
	public AnimeConventionArt(String animeConventionImageUrl, String animeConventionDescription)
	{
		this.animeConventionImageUrl = animeConventionImageUrl;	
		this.animeConventionDescription = animeConventionDescription;
	}
	
    // Getter method for the primary key field
	public Long getAnimeConventionId()
	{
		return animeConventionId;
	}
	
    // Setter method for the primary key field
	public void setAnimeConventionId(Long animeConventionId)
	{
		this.animeConventionId = animeConventionId;
	}
	
    // Getter method for the image URL field
	public String getAnimeConventionImageUrl()
	{
		return animeConventionImageUrl;
	}
	
    // Setter method for the image URL field
	public void setAnimeConventionImageUrl(String animeConventionImageUrl)
	{
		this.animeConventionImageUrl = animeConventionImageUrl;
	}
	
    // Setter method for the description field
	public void setAnimeConventionDescription(String animeConventionDescription)
	{
		this.animeConventionDescription = animeConventionDescription;
	}
	
    // Getter method for the description field
	public String getAnimeConventionDescription()
	{
		return animeConventionDescription;
	}
	
    // Default constructor needed by JPA
	public AnimeConventionArt()
	{
		super();
	}
}