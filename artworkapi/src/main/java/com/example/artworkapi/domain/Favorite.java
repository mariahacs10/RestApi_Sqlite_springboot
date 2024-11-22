package com.example.artworkapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference // This will break the infinite loop by ignoring the back reference
    private All_Images image;

    @ManyToOne
    @JsonBackReference // Breaks recursion from Favorite -> AppUser
    private AppUser user;

    public Favorite() {}

    // Define the constructor to resolve the second error
    public Favorite(All_Images image, AppUser user) {
        this.image = image;
        this.user = user;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public All_Images getImage() {
        return image;
    }

    public void setImage(All_Images image) {
        this.image = image;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
