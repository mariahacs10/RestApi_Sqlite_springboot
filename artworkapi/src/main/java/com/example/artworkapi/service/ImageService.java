package com.example.artworkapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.artworkapi.domain.All_Images;
import com.example.artworkapi.model.All_Images_Repository;

@Service
//The ImageService class is a Spring service component responsible for handling operations related to images.
//It interacts with the All_Images_Repository to fetch image data from the database.

public class ImageService {

 @Autowired
 private All_Images_Repository allImagesRepository;  // Repository for performing CRUD operations on All_Images entities.

 // Method to find an image by its ID.
 // Throws a ResourceNotFoundException if the image is not found in the database.
 public All_Images findById(Long imageId) throws ResourceNotFoundException {
     // Use the repository to find the image by its ID.
     // If the image is not found, throw a ResourceNotFoundException with an appropriate error message.
     return allImagesRepository.findById(imageId)
             .orElseThrow(() -> new ResourceNotFoundException("Image not found for ID: " + imageId));
 }
}
