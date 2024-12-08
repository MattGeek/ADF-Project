package com.example.demo_claude.services;

import com.example.demo_claude.repositories.dto.PetDTO;
import com.example.demo_claude.repositories.dto.PetStatistics;
import com.example.demo_claude.exceptions.PetAlreadyExistsException;
import com.example.demo_claude.exceptions.PetNotFoundException;

import java.util.List;

public interface PetService {
    PetDTO createPet(PetDTO petDTO) throws PetAlreadyExistsException;
    List<PetDTO> getAllPets();
    PetDTO getPetById(Long id) throws PetNotFoundException;
    PetDTO updatePetDetails(Long id, PetDTO petDTO) throws PetNotFoundException;
    void deletePetById(Long id) throws PetNotFoundException;
    void deletePetsByName(String name);
    List<PetDTO> findPetsByAnimalType(String animalType);
    List<PetDTO> findPetsByBreed(String breed);
    List<Object[]> getNamesAndBreeds();
    PetStatistics getPetStatistics();
}
