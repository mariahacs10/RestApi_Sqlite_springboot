package com.example.artworkapi.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artworkapi.domain.All_Images;
import com.example.artworkapi.domain.AppUser;
import com.example.artworkapi.domain.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(AppUser user);
    Optional<Favorite> findByUserAndImage(AppUser user, All_Images image);
}