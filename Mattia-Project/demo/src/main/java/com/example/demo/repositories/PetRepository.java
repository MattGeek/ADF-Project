package com.example.demo.repositories;

import com.example.demo.entities.Pet;
import com.example.demo.repositories.dto.PetDescription;
import com.example.demo.repositories.dto.PetStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findPetsByAnimalType(String animalType);

    List<Pet> findPetsByBreed(String breed);

    @Query("SELECT new com.example.demo.repositories.dto.PetDescription(p.name, p.animalType, p.breed) FROM Pet p")
    List<PetDescription> getPetDescriptions();

    @Query("SELECT new com.example.demo.repositories.dto.PetStatistics(AVG(p.age), MAX(p.age)) FROM Pet p")
    PetStatistics getPetStatistics();

    @Modifying
    @Transactional
    @Query("UPDATE Pet p SET p.name = :newName, p.age = :newAge WHERE p.id = :id")
    int updatePetDetails(@Param("id") long id, @Param("newName") String newName, @Param("newAge") int newAge);
}
