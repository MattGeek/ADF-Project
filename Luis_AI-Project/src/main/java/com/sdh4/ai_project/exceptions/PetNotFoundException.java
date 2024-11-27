package com.sdh4.ai_project.exceptions;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(String message) {
        super(message);
    }
}
