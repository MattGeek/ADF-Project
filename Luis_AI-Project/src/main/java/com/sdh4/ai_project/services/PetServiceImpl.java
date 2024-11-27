package com.sdh4.ai_project.services;

import com.sdh4.ai_project.entities.Pet;
import com.sdh4.ai_project.exceptions.PetNotFoundException;
import com.sdh4.ai_project.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with ID: " + id));
    }

    @Override
    public Pet updatePet(Long id, Pet petDetails) {
        Pet existingPet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with ID: " + id));

        existingPet.setName(petDetails.getName());
        existingPet.setAnimalType(petDetails.getAnimalType());
        existingPet.setBreed(petDetails.getBreed());
        existingPet.setAge(petDetails.getAge());
        return petRepository.save(existingPet);
    }

    @Override
    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public void deletePetsByName(String name) {
        List<Pet> pets = petRepository.findByNameIgnoreCase(name);
        petRepository.deleteAll(pets);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalType(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAgeAsc(breed);
    }

    @Override
    public List<String> getNameAndBreed() {
        return petRepository.findAll().stream()
                .map(pet -> pet.getName() + " - " + pet.getBreed())
                .collect(Collectors.toList());
    }

    @Override
    public String getPetStatistics() {
        List<Pet> pets = petRepository.findAll();
        double avgAge = pets.stream().mapToInt(Pet::getAge).average().orElse(0);
        int maxAge = pets.stream().mapToInt(Pet::getAge).max().orElse(0);
        return "Average Age: " + avgAge + ", Oldest Age: " + maxAge;
    }
}
