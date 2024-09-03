package com.example.artworkapi.service;

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

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private All_Images_Repository allImagesRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public void addFavorite(Long userId, Long imageId) {
        AppUser user = appUserRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        All_Images image = allImagesRepository.findById(imageId)
            .orElseThrow(() -> new ResourceNotFoundException("Image not found"));

        Favorite favorite = new Favorite(image, user);
        favoriteRepository.save(favorite);
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
}