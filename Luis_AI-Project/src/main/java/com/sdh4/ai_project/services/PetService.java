package com.sdh4.ai_project.services;

import com.sdh4.ai_project.entities.Pet;
import java.util.List;

public interface PetService {
    Pet createPet(Pet pet);
    List<Pet> getAllPets();
    Pet getPetById(Long id);
    Pet updatePet(Long id, Pet petDetails);
    void deletePetById(Long id);
    void deletePetsByName(String name);
    List<Pet> findPetsByAnimalType(String animalType);
    List<Pet> findPetsByBreed(String breed);
    List<String> getNameAndBreed();
    String getPetStatistics();
}
