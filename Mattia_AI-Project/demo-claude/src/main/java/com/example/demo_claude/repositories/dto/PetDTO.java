package com.example.demo_claude.repositories.dto;

import com.example.demo_claude.entities.Pet;
import lombok.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
    private Long id;
    private String name;
    private String animalType;
    private String breed;
    private int age;

    public static PetDTO fromEntity(Pet pet) {
        return new PetDTO(
            pet.getId(),
            pet.getName(),
            pet.getAnimalType(),
            pet.getBreed(),
            pet.getAge()
        );
    }
}
