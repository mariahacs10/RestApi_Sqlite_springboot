package com.example.artworkapi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.artworkapi.domain.All_Images;
import com.example.artworkapi.domain.AppUser;
import com.example.artworkapi.domain.Favorite;
import com.example.artworkapi.domain.FavoriteRequest;
import com.example.artworkapi.service.AppUserService;
import com.example.artworkapi.service.FavoriteService;
import com.example.artworkapi.service.ImageService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private AppUserService userService;

    @Autowired
    private ImageService imageService; // Inject ImageService

    @PostMapping("/addFav")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteRequest request) {
        // Validate input
        if (request.getImageId() == null || request.getUserId() == null) {
            return ResponseEntity.badRequest().body("Image ID and User ID are required."); // Bad request if required data is missing
        }

        // Log request data for debugging
        System.out.println("Received add favorite request for User ID: " + request.getUserId() +
                            ", Image ID: " + request.getImageId());

        // Fetch user from the database
        AppUser user = userService.findById(request.getUserId());
        if (user == null) {
            System.out.println("User not found for ID: " + request.getUserId());
            return ResponseEntity.status(404).body("User not found."); // Return error if user does not exist
        }

        // Fetch image from the database using imageId
        All_Images image;
        try {
            image = imageService.findById(request.getImageId());
        } catch (ResourceNotFoundException e) {
            System.out.println("Image not found for ID: " + request.getImageId());
            return ResponseEntity.status(404).body("Image not found."); // Return error if image does not exist
        }

        // Check if the favorite already exists for this user and image
        if (favoriteService.isFavoriteExists(user, image)) {
            System.out.println("Image already favorited by User ID: " + request.getUserId());
            return ResponseEntity.status(400).body("This image is already favorited by the user."); // Prevent duplicate favorites
        }

        // Try adding favorite
        Favorite favorite = favoriteService.addFavorite(image, user);
        System.out.println("Favorite added successfully for User ID: " + request.getUserId());
        return ResponseEntity.ok(favorite); // Success
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<All_Images>> getUserFavorites(@PathVariable Long userId) {
        // Fetch the user's favorite images
        List<All_Images> favorites = favoriteService.getUserFavorites(userId);

        // If no favorites are found, return 404
        if (favorites.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        // Return favorite images as AllImagesItem objects
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/delete/{userId}/{imageId}")
    public ResponseEntity<?> removeFavorite(@PathVariable Long userId, @PathVariable Long imageId) {
        try {
            favoriteService.removeFavorite(userId, imageId);
            return ResponseEntity.ok("Favorite deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    } 
  
}

