package com.sdh4.ai_project.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PetDTO(
        @NotBlank String name,
        @NotBlank String animalType,
        @NotBlank String breed,
        @Min(0) int age,
        @NotBlank String householdEircode
) {}
