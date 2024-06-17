package com.example.artworkapi.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.artworkapi.domain.AnimeConventionArt;

//Declares an interface for a repository that will handle AnimeConventionArt entities
//Extends JpaRepository which provides JPA related methods for standard CRUD operations
public interface AnimeConventionRepository extends JpaRepository<AnimeConventionArt,Long>{

	// The interface does not need to declare any methods as JpaRepository
    // already provides standard methods such as save(), findAll(), findById(), delete(), etc.
    // JpaRepository<AnimeConventionArt, Long>:
    // - AnimeConventionArt: The entity type that this repository manages
    // - Long: The type of the primary key of the entity AnimeConventionArt
	
}


