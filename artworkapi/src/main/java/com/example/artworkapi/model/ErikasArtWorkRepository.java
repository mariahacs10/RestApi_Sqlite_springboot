package com.example.artworkapi.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.artworkapi.domain.ErikasArtWork;

public interface ErikasArtWorkRepository extends JpaRepository<ErikasArtWork, Long> {
    // Declares a custom query method to search for ErikasArtWork entities based on a query string
    @Query("SELECT e FROM ErikasArtWork e WHERE " +
            "LOWER(e.authorImageUrl) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ErikasArtWork> searchErikasArtWorks(@Param("query") String query);

    // Declares a custom query method to retrieve all ErikasArtWork entities
    @Query("SELECT e FROM ErikasArtWork e")
    List<ErikasArtWork> findAllArtWork();
}