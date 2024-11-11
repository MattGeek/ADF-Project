package com.example.demo.services;

import com.example.demo.entities.Pet;
import com.example.demo.repositories.PetRepository;
import com.example.demo.repositories.dto.PetDescription;
import com.example.demo.repositories.dto.PetStatistics;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
@AllArgsConstructor
public class PetServiceImp implements PetService {
    private PetRepository petRepository;

    @Override
    public long createPet(Pet pet) {
        return this.petRepository.save(pet).getId();
    }

    @Override
    public List<Pet> findPets() {
        return this.petRepository.findAll();
    }

    @Override
    public Optional<Pet> findPetById(long id) {
        return this.petRepository.findById(id);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return this.petRepository.findPetsByAnimalType(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return this.petRepository.findPetsByBreed(breed);
    }

    @Override
    public List<PetDescription> getPetDescriptions() {
        return this.petRepository.getPetDescriptions();
    }

    @Override
    public PetStatistics getPetStatistics() {
        return this.petRepository.getPetStatistics();
    }

    @Override
    public int updatePetDetails(long id, String newName, int newAge) {
        return 0;
    }

    @Override
    public int deletePetById(long id) {
        return 0;
    }

    @Override
    public int deletePetsByName(String name) {
        return 0;
    }
}
