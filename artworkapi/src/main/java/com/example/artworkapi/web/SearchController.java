package com.example.artworkapi.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.artworkapi.domain.SearchResult;
import com.example.artworkapi.service.SearchService;

@RestController
@RequestMapping("/api/search")
@CrossOrigin("https://react-carsandboats.onrender.com/")
public class SearchController {
    private final SearchService searchService;

    // Constructor to inject SearchService dependency
    public SearchController(SearchService searchService) {
        this.searchService = searchService;  // Injects SearchService into the controller
    }

    // GET request handler for searching items
    @GetMapping
    public List<SearchResult> search(
            @RequestParam(required = false) String query) {
        // Check if query parameter is null or empty
        if (query == null || query.isEmpty()) {
            // If query is not provided, return all items
            return searchService.getAllItems();
        } else {
            // If query is provided, search for items matching the query
            return searchService.searchAllItems(query);
        }
    }
}
