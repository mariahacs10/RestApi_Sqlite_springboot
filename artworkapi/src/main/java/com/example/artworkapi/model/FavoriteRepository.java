package com.example.artworkapi.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artworkapi.domain.All_Images;
import com.example.artworkapi.domain.AppUser;
import com.example.artworkapi.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId); // Fetch favorites by user

    List<Favorite> findByUser(AppUser user);
    
    // Add this method to resolve the first error
    Optional<Favorite> findByUserAndImage(AppUser user, All_Images image);

}
