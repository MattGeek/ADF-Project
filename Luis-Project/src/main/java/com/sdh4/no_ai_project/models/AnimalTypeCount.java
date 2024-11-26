package com.sdh4.no_ai_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnimalTypeCount {
    private String animalType;
    private Long count;
}
