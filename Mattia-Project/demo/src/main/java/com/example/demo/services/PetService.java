package com.example.demo.services;

import com.example.demo.entities.Pet;
import com.example.demo.repositories.dto.PetDescription;
import com.example.demo.repositories.dto.PetStatistics;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PetService {
    long createPet(Pet pet);

    List<Pet> findPets();

    Optional<Pet> findPetById(long id);

    List<Pet> findPetsByAnimalType(String animalType);

    List<Pet> findPetsByBreed(String breed);

    List<PetDescription> getPetDescriptions();

    PetStatistics getPetStatistics();

    int updatePetDetails(long id, String newName, int newAge);

    int deletePetById(long id);

    int deletePetsByName(String name);
}
