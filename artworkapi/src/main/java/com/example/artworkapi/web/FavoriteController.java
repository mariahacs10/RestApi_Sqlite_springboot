package com.example.artworkapi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.artworkapi.domain.AppUser;
import com.example.artworkapi.domain.Favorite;
import com.example.artworkapi.domain.FavoriteRequest;
import com.example.artworkapi.service.AppUserService;
import com.example.artworkapi.service.DuplicateResourceException;
import com.example.artworkapi.service.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private AppUserService userService;
    
    @PostMapping("/addFav")
    public ResponseEntity<Favorite> addFavorite(@RequestBody FavoriteRequest request) {
        // Validate input
        if (request.getImageUrl() == null || request.getUserId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        
        AppUser user = userService.findById(request.getUserId());
        if (user != null) {
            try {
                Favorite favorite = favoriteService.addFavorite(request.getImageUrl(), user);
                return ResponseEntity.ok(favorite);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(404).body(null); // Image not found
            } catch (DuplicateResourceException e) {
                return ResponseEntity.status(400).body(null); // Already favorited
            }
        }
        return ResponseEntity.status(404).body(null); // User not found
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Favorite>> getUserFavorites(@PathVariable Long userId) {
        List<Favorite> favorites = favoriteService.getUserFavorites(userId);
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/delete/{favoriteId}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long favoriteId) {
        favoriteService.deleteFavorite(favoriteId);
        return ResponseEntity.ok().build();
    }
}

