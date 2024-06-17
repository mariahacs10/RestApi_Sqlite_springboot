package com.example.artworkapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//this is the entitie class for erikas artwork
@Entity
@Table(name="artwork")
public class ErikasArtWork {

	//for now i am just calling the artwork image url
	//image
	@Id
	@GeneratedValue
	private Long artWorkId;
    private String authorImageUrl; 
	
	public ErikasArtWork(String authorImageUrl)
	{
		this.authorImageUrl = authorImageUrl;	
	}
	
	//Setting up the getters and setters
	public Long getArtWorkId()
	{
		return artWorkId;
	}
	
	public void setArtWorkId(Long artWorkId)
	{
		this.artWorkId = artWorkId;
	}
	
	
	public String getAuthorImageUrl()
	{
		return authorImageUrl;
	}
	
	public void setAuthorImageUrl(String authorImageUrl)
	{
		this.authorImageUrl = authorImageUrl;
	}
	public ErikasArtWork()
	{
		super();
	}
}