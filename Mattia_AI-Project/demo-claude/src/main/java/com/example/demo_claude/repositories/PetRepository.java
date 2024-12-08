package com.example.demo_claude.repositories;

import com.example.demo_claude.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByAnimalTypeIgnoreCase(String animalType);
    List<Pet> findByBreedOrderByAgeAsc(String breed);
    List<Pet> findByNameIgnoreCaseContaining(String name);
    Optional<Pet> findByName(String name);
    long countByAnimalTypeIgnoreCase(String animalType);
    @Query("SELECT p.name, p.animalType, p.breed FROM Pet p")
    List<Object[]> getNamesAndBreeds();
}
