package com.example.artworkapi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.artworkapi.domain.All_Images;
import com.example.artworkapi.service.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/{userId}/{imageId}")
    public ResponseEntity<?> addFavorite(@PathVariable Long userId, @PathVariable Long imageId) {
        favoriteService.addFavorite(userId, imageId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/{imageId}")
    public ResponseEntity<?> removeFavorite(@PathVariable Long userId, @PathVariable Long imageId) {
        favoriteService.removeFavorite(userId, imageId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<All_Images>> getFavoriteImages(@PathVariable Long userId) {
        List<All_Images> favorites = favoriteService.getFavoriteImages(userId);
        return ResponseEntity.ok(favorites);
    }
}