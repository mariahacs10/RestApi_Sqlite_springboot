package com.example.artworkapi.service;


@SuppressWarnings("serial")
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
