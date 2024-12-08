package com.example.demo_claude.exceptions;

public class PetNotFoundException extends Exception {
    public PetNotFoundException(String message) {
        super(message);
    }
}
