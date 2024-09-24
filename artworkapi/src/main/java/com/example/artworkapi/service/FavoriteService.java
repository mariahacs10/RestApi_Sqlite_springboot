package com.example.artworkapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private All_Images_Repository allImagesRepository;

    @Autowired
    private AppUserRepository appUserRepository;
    

    public Favorite addFavorite(String imageUrl, AppUser user) {
        // Find image by URL using the updated method
        Optional<All_Images> imageOptional = allImagesRepository.findByAllImageUrl(imageUrl);
        if (!imageOptional.isPresent()) {
            throw new ResourceNotFoundException("Image not found with the given URL");
        }

        All_Images image = imageOptional.get();

        // Check if the image is already favorited by the user
        boolean alreadyFavorited = favoriteRepository.findByUserAndImage(user, image).isPresent();
        if (alreadyFavorited) {
            throw new DuplicateResourceException("This image is already in the user's favorites");
        }

        // Add the image to the user's favorites
        Favorite favorite = new Favorite(image, user);
        return favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long userId, Long imageId) {
        AppUser user = appUserRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        All_Images image = allImagesRepository.findById(imageId)
            .orElseThrow(() -> new ResourceNotFoundException("Image not found"));

        Favorite favorite = favoriteRepository.findByUserAndImage(user, image)
            .orElseThrow(() -> new ResourceNotFoundException("Favorite not found"));

        favoriteRepository.delete(favorite);
    }

    public List<All_Images> getFavoriteImages(Long userId) {
        AppUser user = appUserRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return favoriteRepository.findByUser(user).stream()
            .map(Favorite::getImage)
            .collect(Collectors.toList());
    }

    public List<Favorite> getUserFavorites(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public void deleteFavorite(Long favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }
    
}