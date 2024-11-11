package com.sdh4.no_ai_project.services;

import com.sdh4.no_ai_project.entities.Pet;
import com.sdh4.no_ai_project.repositories.PetRepository;
import com.sdh4.no_ai_project.services.exceptions.BadDataException;
import com.sdh4.no_ai_project.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class PetServiceImpl implements PetService {
    private PetRepository petRepository;

    @Override
    public Pet createPet(Pet pet) throws BadDataException {
        if (pet == null) {
            throw new BadDataException("pet cannot be null");
        }

        return petRepository.save(pet);
    }

    @Override
    public Pet getPetById(long id) throws NotFoundException {
        Optional<Pet> pet = petRepository.findById(id);

        if (pet.isPresent()) {
            return pet.get();
        } else {
            throw new NotFoundException("no pet found with id " + id);
        }
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> getAllPetsNameTypeBreed() throws BadDataException {
        return List.of();
    }

    @Override
    public List<Pet> getPetsByType(String type) throws BadDataException {
        if (type.isEmpty()) {
            throw new BadDataException("type cannot be empty");
        }

        return petRepository.getPetsByAnimalType(type);
    }

    @Override
    public List<Pet> getPetsByBreed(String breed) throws BadDataException {
        if (breed.isEmpty()) {
            throw new BadDataException("breed cannot be empty");
        }

        return petRepository.getPetsByBreed(breed);
    }

    @Override
    public Pet updatePetName(long id, String name) throws NotFoundException, BadDataException {
        if (name.isEmpty()) {
            throw new BadDataException("name cannot be empty");
        }

        int rowsAffected = petRepository.updatePetName(id, name);

        if (rowsAffected == 0) {
            throw new NotFoundException("no pet found with id " + id);
        }

        return getPetById(id);
    }

    @Override
    public Pet updatePetType(long id, String type) throws NotFoundException, BadDataException {
        if (type.isEmpty()) {
            throw new BadDataException("type cannot be empty");
        }

        int rowsAffected = petRepository.updatePetType(id, type);

        if (rowsAffected == 0) {
            throw new NotFoundException("no pet found with id " + id);
        }

        return getPetById(id);
    }

    @Override
    public Pet updatePetBreed(long id, String breed) throws NotFoundException, BadDataException {
        if (breed.isEmpty()) {
            throw new BadDataException("breed cannot be empty");
        }

        int rowsAffected = petRepository.updatePetBreed(id, breed);

        if (rowsAffected == 0) {
            throw new NotFoundException("no pet found with id " + id);
        }

        return getPetById(id);
    }

    @Override
    public Pet updatePetAge(long id, int age) throws NotFoundException, BadDataException {
        if (age == 0) {
            throw new BadDataException("age cannot be 0");
        }

        int rowsAffected = petRepository.updatePetAge(id, age);

        if (rowsAffected == 0) {
            throw new NotFoundException("no pet found with id " + id);
        }

        return getPetById(id);
    }

    @Override
    public void deletePetById(long id) {
        petRepository.deleteById(id);
    }

    @Override
    public int deletePetsByName(String name) throws NotFoundException {
        int rowsAffected = petRepository.deletePetsByName(name);
        if (rowsAffected == 0) {
            throw new NotFoundException("no pet found with name " + name);
        }

        return rowsAffected;
    }


}
