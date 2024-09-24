package com.example.artworkapi.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.artworkapi.domain.All_Images;

@RepositoryRestResource(exported = false)
public interface All_Images_Repository extends JpaRepository<All_Images, Long> {
	
    List<All_Images> findByCategory(String category);
    
    Optional<All_Images> findByAllImageUrl(String allImageUrl);
    
}
