package com.example.artworkapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.artworkapi.domain.All_Images;
import com.example.artworkapi.domain.AppUser;
import com.example.artworkapi.domain.Favorite;
import com.example.artworkapi.model.All_Images_Repository;
import com.example.artworkapi.model.AppUserRepository;
import com.example.artworkapi.model.FavoriteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
// The FavoriteService class is a Spring service component responsible for managing operations related to user favorites.
// @Service indicates it's a service layer component, and @Transactional ensures that methods are executed within a transaction context.

public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository; // Repository for Favorite entities.

    @Autowired
    private All_Images_Repository allImagesRepository; // Repository for All_Images entities.

    @Autowired
    private AppUserRepository appUserRepository; // Repository for AppUser entities.

    // Method to add a favorite image for a user.
    // Throws DuplicateResourceException if the favorite already exists.
    public Favorite addFavorite(All_Images image, AppUser user) throws DuplicateResourceException {
        // Check if the favorite already exists for the user and image.
        if (favoriteRepository.existsByImageAndUser(image, user)) {
            // If it exists, throw an exception indicating duplication.
            throw new DuplicateResourceException("Favorite already exists");
        }

        // Create a new Favorite entity associating the user and image.
        Favorite favorite = new Favorite(image, user);
        // Save the Favorite entity to the database and return it.
        return favoriteRepository.save(favorite);
    }

    // Method to check if a favorite exists for a given user and image.
    public boolean isFavoriteExists(AppUser user, All_Images image) {
        // Return true if the favorite exists; false otherwise.
        return favoriteRepository.existsByImageAndUser(image, user);
    }

    // Method to remove a favorite given the user ID and image ID.
    public void removeFavorite(Long userId, Long imageId) {
        // Fetch user and image by their respective IDs
        AppUser user = appUserRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        
        All_Images image = allImagesRepository.findById(imageId)
            .orElseThrow(() -> new ResourceNotFoundException("Image not found with id " + imageId));

        // Find the favorite based on user and image
        Favorite favorite = favoriteRepository.findByUserAndImage(user, image)
            .orElseThrow(() -> new ResourceNotFoundException("Favorite not found for userId " + userId + " and imageId " + imageId));

        // If favorite exists, delete it
        favoriteRepository.delete(favorite);
    }


    // Method to get all favorite images for a user.
    public List<All_Images> getFavoriteImages(Long userId) {
        // Find the user by ID or throw an exception if not found.
        AppUser user = appUserRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Find all favorites for the user, map them to their associated images, and collect into a list.
        return favoriteRepository.findByUser(user).stream()
            .map(Favorite::getImage)
            .collect(Collectors.toList());
    }

    // Method to fetch favorites for a user and map them to All_Images entities.
    // Note: This method appears to be redundant with getFavoriteImages and may need updating.
    public List<All_Images> getUserFavorites(Long userId) {
        // Fetch the user by ID or throw an exception if not found.
        AppUser user = appUserRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Fetch favorites for the user and map them to new All_Images objects.
        return favoriteRepository.findByUser(user).stream()
            .map(favorite -> {
                All_Images image = favorite.getImage();
                // Create and return a new All_Images object with specific fields.
                return new All_Images(
                    image.getAllImageId(),                // Include this line to set the correct ID
                    image.getallImageUrl(),          // Image URL
                    image.getAllImageDescriptions(), // Image descriptions
                    image.getCategory()              // Image category
                );
            })
            .collect(Collectors.toList());
    }    
}
