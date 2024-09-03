package com.example.artworkapi.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.artworkapi.domain.AppUser;
import com.example.artworkapi.model.AppUserRepository;

//Marks this class as a service component in Spring
@Service
//Implements the UserDetailsService interface provided by Spring Security
public class UserDetailsServiceImpl implements UserDetailsService {
 
 // Repository to access user data from the database
 private final AppUserRepository repository;

 // Constructor for dependency injection of the AppUserRepository
 public UserDetailsServiceImpl(AppUserRepository repository) {
     this.repository = repository;
 }

 // Overrides the loadUserByUsername method from UserDetailsService
 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     // Finds the user by username using the repository
     Optional<AppUser> user = repository.findByUsername(username);
     UserBuilder builder = null;
     
     // If user is present, build the UserDetails object
     if (user.isPresent()) {
         AppUser currentUser = user.get();
         builder = org.springframework.security.core.userdetails.User.withUsername(username);
         builder.password(currentUser.getPassword())
         .roles(currentUser.getRole()); // Add the role here
     } else {
         // If user is not found, throw UsernameNotFoundException
         throw new UsernameNotFoundException("User not found.");
     }
     
     // Returns the built UserDetails object
     return builder.build();
 }
}
