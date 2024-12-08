package com.example.demo_claude.repositories.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetStatistics {
    private long totalPets;
    private int averageAge;
    private int oldestAge;
}
