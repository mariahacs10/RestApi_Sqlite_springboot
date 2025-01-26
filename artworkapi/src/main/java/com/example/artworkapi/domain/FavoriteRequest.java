package com.example.artworkapi.domain;

public class FavoriteRequest {

    private Long imageId;  // Add imageId to link to an image in the database
    private Long userId;

    // Getters and setters
    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

