package com.example.artworkapi.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.artworkapi.domain.AppUser;

@RepositoryRestResource(exported = false)
public interface AppUserRepository extends JpaRepository 
<AppUser, Long> {
	//This method is used to find a user from the database in the authentication process. The method 
	//returns Optional to prevent a null exception. 
    Optional<AppUser> findByUsername(String username);
 }
