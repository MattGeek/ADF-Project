package com.sdh4.ai_project.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HouseholdDTO(
        @NotBlank String eircode,
        @Min(0) int numberOfOccupants,
        @Min(1) @Max(20) int maxNumberOfOccupants,
        @NotNull Boolean ownerOccupied
) {}
