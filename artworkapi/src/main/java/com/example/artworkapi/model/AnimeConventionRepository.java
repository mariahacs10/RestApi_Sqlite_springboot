package com.example.artworkapi.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.artworkapi.domain.AnimeConventionArt;

//Declares an interface for a repository that will handle AnimeConventionArt entities
//Extends JpaRepository which provides JPA related methods for standard CRUD operations
public interface AnimeConventionRepository extends JpaRepository<AnimeConventionArt, Long> {
    // Declares a custom query method to search for AnimeConventionArt entities based on a query string
	@Query("SELECT a FROM AnimeConventionArt a WHERE " +
            "LOWER(a.animeConventionImageUrl) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(a.animeConventionDescription) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<AnimeConventionArt> searchAnimeConventionArts(@Param("query") String query);

    // Declares a custom query method to retrieve all AnimeConventionArt entities
    @Query("SELECT a FROM AnimeConventionArt a")
    List<AnimeConventionArt> findAllAnime();
}