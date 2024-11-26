package com.sdh4.no_ai_project.services;

import com.sdh4.no_ai_project.entities.Pet;
import com.sdh4.no_ai_project.models.AnimalTypeCount;
import com.sdh4.no_ai_project.services.exceptions.BadDataException;
import com.sdh4.no_ai_project.services.exceptions.NotFoundException;

import java.util.List;

public interface PetService {
    Pet createPet(Pet pet) throws BadDataException;

    // Get services
    Pet getPetById(long id) throws NotFoundException;
    List<Pet> getAllPets();
    List<Pet> getAllPetsNameTypeBreed() throws BadDataException;
    List<Pet> getPetsByType(String type) throws BadDataException;
    List<Pet> getPetsByBreed(String breed) throws BadDataException;
    List<AnimalTypeCount> getPetsByAnimalTypeStats();

    // Update services
    Pet updatePetName(long id, String name) throws NotFoundException, BadDataException;
    Pet updatePetType(long id, String type) throws NotFoundException, BadDataException;
    Pet updatePetBreed(long id, String breed) throws NotFoundException, BadDataException;
    Pet updatePetAge(long id, int age) throws NotFoundException, BadDataException;

    // Delete services
    void deletePetById(long id);
    int deletePetsByName(String name) throws NotFoundException;
}
