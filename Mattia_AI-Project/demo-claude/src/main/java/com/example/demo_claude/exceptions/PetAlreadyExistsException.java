package com.example.demo_claude.exceptions;

public class PetAlreadyExistsException extends Exception {
    public PetAlreadyExistsException(String message) {
        super(message);
    }
}
