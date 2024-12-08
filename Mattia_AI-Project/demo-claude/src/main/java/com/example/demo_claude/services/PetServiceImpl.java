package com.example.demo_claude.services;

import com.example.demo_claude.repositories.dto.PetDTO;
import com.example.demo_claude.repositories.dto.PetStatistics;
import com.example.demo_claude.entities.Pet;
import com.example.demo_claude.exceptions.PetAlreadyExistsException;
import com.example.demo_claude.exceptions.PetNotFoundException;
import com.example.demo_claude.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public PetDTO createPet(PetDTO petDTO) throws PetAlreadyExistsException {
        Optional<Pet> existingPet = petRepository.findByName(petDTO.getName());
        if (existingPet.isPresent()) {
            throw new PetAlreadyExistsException("Pet with name " + petDTO.getName() + " already exists.");
        }
        Pet newPet = new Pet(null, petDTO.getName(), petDTO.getAnimalType(), petDTO.getBreed(), petDTO.getAge());
        Pet savedPet = petRepository.save(newPet);
        return PetDTO.fromEntity(savedPet);
    }

    @Override
    public List<PetDTO> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map(PetDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO getPetById(Long id) throws PetNotFoundException {
        Optional<Pet> petOptional = petRepository.findById(id);
        if (petOptional.isEmpty()) {
            throw new PetNotFoundException("Pet with ID " + id + " not found.");
        }
        return PetDTO.fromEntity(petOptional.get());
    }

    @Override
    public PetDTO updatePetDetails(Long id, PetDTO petDTO) throws PetNotFoundException {
        Optional<Pet> petOptional = petRepository.findById(id);
        if (petOptional.isEmpty()) {
            throw new PetNotFoundException("Pet with ID " + id + " not found.");
        }
        Pet existingPet = petOptional.get();
        existingPet.setName(petDTO.getName());
        existingPet.setAnimalType(petDTO.getAnimalType());
        existingPet.setBreed(petDTO.getBreed());
        existingPet.setAge(petDTO.getAge());
        Pet updatedPet = petRepository.save(existingPet);
        return PetDTO.fromEntity(updatedPet);
    }

    @Override
    public void deletePetById(Long id) throws PetNotFoundException {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException("Pet with ID " + id + " not found.");
        }
        petRepository.deleteById(id);
    }

    @Override
    public void deletePetsByName(String name) {
        List<Pet> pets = petRepository.findByNameIgnoreCaseContaining(name);
        petRepository.deleteAll(pets);
    }

    @Override
    public List<PetDTO> findPetsByAnimalType(String animalType) {
        List<Pet> pets = petRepository.findByAnimalTypeIgnoreCase(animalType);
        return pets.stream()
                .map(PetDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDTO> findPetsByBreed(String breed) {
        List<Pet> pets = petRepository.findByBreedOrderByAgeAsc(breed);
        return pets.stream()
                .map(PetDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Object[]> getNamesAndBreeds() {
        return petRepository.getNamesAndBreeds();
    }

    @Override
    public PetStatistics getPetStatistics() {
        List<Pet> pets = petRepository.findAll();
        long totalPets = pets.size();
        int totalAge = pets.stream()
                .mapToInt(Pet::getAge)
                .sum();
        int averageAge = totalAge / (int) totalPets;
        int oldestAge = pets.stream()
                .mapToInt(Pet::getAge)
                .max()
                .orElse(0);
        return new PetStatistics(totalPets, averageAge, oldestAge);
    }
}
