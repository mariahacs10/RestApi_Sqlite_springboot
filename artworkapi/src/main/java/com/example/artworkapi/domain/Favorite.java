package com.example.artworkapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private All_Images image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;

    // Default constructor
    public Favorite() {
    }

    // Constructor with fields
    public Favorite(All_Images image, AppUser user) {
        this.image = image;
        this.user = user;
    }

    // Getters and Setters
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

    // toString method for debugging
    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", image=" + (image != null ? image.getAllImagesId() : null) +
                ", user=" + (user != null ? user.getId() : null) +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favorite)) return false;
        Favorite favorite = (Favorite) o;
        return getId() != null && getId().equals(favorite.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}