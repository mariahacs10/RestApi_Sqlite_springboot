package com.example.artworkapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.artworkapi.domain.All_Images;
import com.example.artworkapi.model.All_Images_Repository;

//@Service
////The ImageService class is a Spring service component responsible for handling operations related to images.
////It interacts with the All_Images_Repository to fetch image data from the database.
//
//public class ImageService {
//
// @Autowired
// private All_Images_Repository allImagesRepository;  // Repository for performing CRUD operations on All_Images entities.
//
// // Method to find an image by its ID.
// // Throws a ResourceNotFoundException if the image is not found in the database.
// public All_Images findById(Long imageId) throws ResourceNotFoundException {
//     // Use the repository to find the image by its ID.
//     // If the image is not found, throw a ResourceNotFoundException with an appropriate error message.
//     return allImagesRepository.findById(imageId)
//             .orElseThrow(() -> new ResourceNotFoundException("Image not found for ID: " + imageId));
// }
//}

@Service
public class ImageService {
    // Repository for database operations
    private final All_Images_Repository allImagesRepository;

    @Autowired
    public ImageService(All_Images_Repository allImagesRepository) {
        this.allImagesRepository = allImagesRepository;
    }

    // Find image by ID with error handling
    public All_Images findById(Long imageId) throws ResourceNotFoundException {
        return allImagesRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found for ID: " + imageId));
    }

    // Get all images by category
    public List<All_Images> getAllImagesByCategory(String category) {
        return allImagesRepository.findByCategory(category);
    }

    // Add new image to database
    public All_Images addImage(String imageUrl, String description, String category) {
        if (allImagesRepository.existsByAllImageUrl(imageUrl)) {
            throw new IllegalArgumentException("Image URL already exists");
        }
        All_Images newImage = new All_Images(null, imageUrl, description, category);
        return allImagesRepository.save(newImage);
    }

    // Update existing image
    public All_Images updateImage(Long imageId, String imageUrl, String description, String category) {
        All_Images image = findById(imageId);
        image.setAllImageUrl(imageUrl);
        image.setAllImageDescriptions(description);
        image.setCategory(category);
        return allImagesRepository.save(image);
    }

    // Delete image by ID
    public void deleteImage(Long imageId) {
        if (!allImagesRepository.existsById(imageId)) {
            throw new ResourceNotFoundException("Image not found for ID: " + imageId);
        }
        allImagesRepository.deleteById(imageId);
    }

    // Get all images sorted by category and ID
    public List<All_Images> getAllImagesSorted() {
        return allImagesRepository.findAllByOrderByCategoryAscAllImagesIdAsc();
    }
}