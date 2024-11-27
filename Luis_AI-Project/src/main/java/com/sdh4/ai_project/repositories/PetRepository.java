package com.sdh4.ai_project.repositories;

import com.sdh4.ai_project.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByAnimalType(String animalType);
    List<Pet> findByBreedOrderByAgeAsc(String breed);
    List<Pet> findByNameIgnoreCase(String name);
}
