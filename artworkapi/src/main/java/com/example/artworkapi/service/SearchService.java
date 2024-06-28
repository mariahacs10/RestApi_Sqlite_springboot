package com.example.artworkapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.artworkapi.domain.AnimeConventionArt;
import com.example.artworkapi.domain.ErikasArtWork;
import com.example.artworkapi.domain.SearchResult;
import com.example.artworkapi.model.AnimeConventionRepository;
import com.example.artworkapi.model.ErikasArtWorkRepository;
@Service
public class SearchService {
    // Dependency injection of repositories
    private final AnimeConventionRepository animeConventionRepository;
    private final ErikasArtWorkRepository erikasArtWorkRepository;

    // Constructor to inject dependencies
    public SearchService(AnimeConventionRepository animeConventionRepository,
                         ErikasArtWorkRepository erikasArtWorkRepository) {
        this.animeConventionRepository = animeConventionRepository;   // Inject AnimeConventionRepository
        this.erikasArtWorkRepository = erikasArtWorkRepository;       // Inject ErikasArtWorkRepository
    }

    // Method to retrieve all items from both repositories and convert them to SearchResult objects
    public List<SearchResult> getAllItems() {
        List<SearchResult> results = new ArrayList<>();

        // Retrieve all AnimeConventionArt entities, convert them to SearchResult, and add to results list
        results.addAll(animeConventionRepository.findAllAnime().stream()
                .map(this::convertToSearchResult)
                .collect(Collectors.toList()));

        // Retrieve all ErikasArtWork entities, convert them to SearchResult, and add to results list
        results.addAll(erikasArtWorkRepository.findAllArtWork().stream()
                .map(this::convertToSearchResult)
                .collect(Collectors.toList()));

        return results;  // Return the combined list of SearchResult objects
    }

    // Method to search for items matching a query in both repositories and convert them to SearchResult objects
    public List<SearchResult> searchAllItems(String query) {
        List<SearchResult> results = new ArrayList<>();

        // Search for AnimeConventionArt entities matching the query, convert them to SearchResult, and add to results list
        results.addAll(animeConventionRepository.searchAnimeConventionArts(query).stream()
                .map(this::convertToSearchResult)
                .collect(Collectors.toList()));

        // Search for ErikasArtWork entities matching the query, convert them to SearchResult, and add to results list
        results.addAll(erikasArtWorkRepository.searchErikasArtWorks(query).stream()
                .map(this::convertToSearchResult)
                .collect(Collectors.toList()));

        return results;  // Return the combined list of SearchResult objects matching the query
    }
    
    // Helper method to convert AnimeConventionArt entity to SearchResult object
    private SearchResult convertToSearchResult(AnimeConventionArt art) {
        return art != null ? new SearchResult("AnimeConventionArt", art.getAnimeConventionId(), 
                art.getAnimeConventionImageUrl(), art.getAnimeConventionDescription()) : null;
    }

    // Helper method to convert ErikasArtWork entity to SearchResult object
    private SearchResult convertToSearchResult(ErikasArtWork art) {
        return art != null ? new SearchResult("ErikasArtWork", art.getArtWorkId(), 
                art.getAuthorImageUrl(), null) : null;
    }
}
