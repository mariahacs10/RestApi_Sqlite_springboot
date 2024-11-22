package com.example.artworkapi.service;


@SuppressWarnings("serial")
//This class defines a custom exception for handling duplicate resources.
//It extends RuntimeException, meaning it is an unchecked exception.

public class DuplicateResourceException extends RuntimeException {

 // Constructor that takes an error message as a parameter and passes it to the superclass (RuntimeException).
 public DuplicateResourceException(String message) {
     super(message);
 }
}
