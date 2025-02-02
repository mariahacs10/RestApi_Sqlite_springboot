package com.example.artworkapi.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.artworkapi.domain.All_Images;

//@RepositoryRestResource(exported = false)
////This interface serves as the repository for the All_Images entity.
////It extends JpaRepository, providing CRUD operations and additional query methods.
////The annotation `@RepositoryRestResource(exported = false)` indicates that this repository will not be exposed directly as a REST resource.
//
//public interface All_Images_Repository extends JpaRepository<All_Images, Long> {
//
// // Method to retrieve a list of All_Images entities based on the category.
// List<All_Images> findByCategory(String category);
//
// // Method to find an All_Images entity by its image URL.
// Optional<All_Images> findByAllImageUrl(String allImageUrl);
//}


@RepositoryRestResource(exported = false)
public interface All_Images_Repository extends JpaRepository<All_Images, Long> {
    // Finds image by URL, used to check for duplicates
    Optional<All_Images> findByAllImageUrl(String allImageUrl);
    
    // Finds all images by category (anime, jjk, etc)
    List<All_Images> findByCategory(String category);
    
    // Finds all images sorted by category and ID
    List<All_Images> findAllByOrderByCategoryAscAllImagesIdAsc();
    
    // Checks if an image URL already exists
    boolean existsByAllImageUrl(String allImageUrl);
}