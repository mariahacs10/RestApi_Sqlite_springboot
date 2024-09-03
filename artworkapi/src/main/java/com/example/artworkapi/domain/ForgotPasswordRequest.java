package com.example.artworkapi.domain;

public class ForgotPasswordRequest {
    private String email;

    // Default constructor
    public ForgotPasswordRequest() {
    }

    // Constructor
    public ForgotPasswordRequest(String email) {
        this.email = email;
    }

    // Getter and setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
