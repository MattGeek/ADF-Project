package com.sdh4.no_ai_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Statistics {
    private int totalHouseholds;
    private int totalPets;
    private List<AnimalTypeCount> petsByAnimalType;
}
