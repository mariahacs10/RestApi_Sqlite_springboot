package com.example.artworkapi.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
     // Find the user by username
     AppUser currentUser = repository.findByUsername(username)
         .orElseThrow(() -> new UsernameNotFoundException("User not found."));
     
     // Create and return UserDetailsImpl with all required information
     return new UserDetailsImpl(
         currentUser.getId(),
         currentUser.getUsername(),
         currentUser.getPassword(),
         Collections.singletonList(new SimpleGrantedAuthority(currentUser.getRole()))
     );
 }
}
