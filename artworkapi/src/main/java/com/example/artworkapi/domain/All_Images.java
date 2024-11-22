package com.example.artworkapi.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//Marks this class as a JPA entity, so it will be mapped to a database table
@Entity
//Specifies the name of the table in the database that this entity will be mapped to
@Table(name="all images table")
public class All_Images {

	 // Marks this field as the primary key of the entity
	@Id
	// Specifies that the primary key value will be generated automatically
	@GeneratedValue
	private Long allImagesId;

	// Field to store the URL of the anime convention image
    private String allImageUrl;
    private String allImageDescriptions;
    
    private String category;
    
    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    @JsonManagedReference // This will manage the reference from All_Images to Favorite
    private Set<Favorite> favorites = new HashSet<>();
    
    
    // Constructor to initialize the entity with an image URL and description
	public All_Images(Long allImagesId, String allImageUrl, String allImageDescriptions, String category)
	{
	    this.allImagesId = allImagesId;  // Ensure this field is set properly
		this.allImageUrl = allImageUrl;
		this.category = category;
		this.allImageDescriptions = allImageDescriptions;
	}
	
	public Long getAllImageId()
	{
		return allImagesId;
	}
	
	public void setAllImageId(Long allImagesId)
	{
		this.allImagesId = allImagesId;
	}
	
	public void addFavorite(Favorite favorite) {
	     favorites.add(favorite);
	     favorite.setImage(this);
	 }
	
	public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
	

    public void removeFavorite(Favorite favorite) {
	      favorites.remove(favorite);
	      favorite.setImage(null);
	}

	// Getter and setter for favorites
	public Set<Favorite> getFavorites() {
	     return favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
	     this.favorites = favorites;
	}
	
    // Getter method for the primary key field
	public Long getAllImagesId()
	{
		return allImagesId;
	}
	
    // Setter method for the primary key field
	public void setAllImagesId(Long allImagesId)
	{
		this.allImagesId = allImagesId;
	}
	
    // Getter method for the image URL field
	public String getallImageUrl()
	{
		return allImageUrl;
	}
	
    // Setter method for the image URL field
	public void setAllImageUrl(String allImageUrl)
	{
		this.allImageUrl = allImageUrl;
	}
	
	
	public String getAllImageDescriptions()
	{
		return allImageDescriptions;	
	}
	
	public void setAllImageDescriptions(String allImageDescriptions)
	{
		this.allImageDescriptions =allImageDescriptions;
	}

	
    // Default constructor needed by JPA
	public All_Images()
	{
		super();
	}
}