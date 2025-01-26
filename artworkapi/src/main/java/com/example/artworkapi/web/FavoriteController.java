package com.example.artworkapi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    private ImageService imageService;

    @PostMapping("/addFav")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteRequest request) {
        // Log the request payload
        System.out.println("[DEBUG] Received add favorite request: " + request);

        // Validate input
        if (request.getImageId() == null || request.getUserId() == null) {
            System.out.println("[ERROR] Missing Image ID or User ID in request.");
            return ResponseEntity.badRequest().body("Image ID and User ID are required.");
        }

        // Log user and image lookup
        System.out.println("[DEBUG] Looking up User ID: " + request.getUserId());
        AppUser user = userService.findById(request.getUserId());
        if (user == null) {
            System.out.println("[ERROR] User not found for ID: " + request.getUserId());
            return ResponseEntity.status(404).body("User not found.");
        }

        System.out.println("[DEBUG] Looking up Image ID: " + request.getImageId());
        All_Images image;
        try {
            image = imageService.findById(request.getImageId());
        } catch (ResourceNotFoundException e) {
            System.out.println("[ERROR] Image not found for ID: " + request.getImageId());
            return ResponseEntity.status(404).body("Image not found.");
        }

        // Check for duplicate favorites
        System.out.println("[DEBUG] Checking if Image ID: " + request.getImageId() + 
                           " is already favorited by User ID: " + request.getUserId());
        if (favoriteService.isFavoriteExists(user, image)) {
            System.out.println("[INFO] Image already favorited by User ID: " + request.getUserId());
            return ResponseEntity.status(400).body("This image is already favorited by the user.");
        }

        // Add the favorite
        Favorite favorite = favoriteService.addFavorite(image, user);
        System.out.println("[SUCCESS] Favorite added successfully for User ID: " + request.getUserId() +
                           " and Image ID: " + request.getImageId());
        return ResponseEntity.ok(favorite);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<All_Images>> getUserFavorites(@PathVariable Long userId) {
        // Log the user lookup
        System.out.println("[DEBUG] Fetching favorites for User ID: " + userId);
        List<All_Images> favorites = favoriteService.getUserFavorites(userId);

        if (favorites.isEmpty()) {
            System.out.println("[INFO] No favorites found for User ID: " + userId);
            return ResponseEntity.status(404).body(null);
        }

        System.out.println("[SUCCESS] Favorites retrieved for User ID: " + userId + " - Count: " + favorites.size());
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/delete/{userId}/{imageId}")
    public ResponseEntity<?> removeFavorite(@PathVariable Long userId, @PathVariable Long imageId) {
        // Log the delete request
        System.out.println("[DEBUG] Received delete request for User ID: " + userId + 
                           " and Image ID: " + imageId);
        try {
            favoriteService.removeFavorite(userId, imageId);
            System.out.println("[SUCCESS] Favorite deleted for User ID: " + userId + 
                               " and Image ID: " + imageId);
            return ResponseEntity.ok("Favorite deleted successfully");
        } catch (ResourceNotFoundException e) {
            System.out.println("[ERROR] Favorite not found for User ID: " + userId + 
                               " and Image ID: " + imageId + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}