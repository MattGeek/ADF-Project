package com.sdh4.ai_project.controllers;

import com.sdh4.ai_project.dtos.PetDTO;
import com.sdh4.ai_project.entities.Pet;
import com.sdh4.ai_project.services.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Pet getPet(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createPet(@Valid @RequestBody PetDTO petDTO) {
        Pet pet = new Pet(null, petDTO.name(), petDTO.animalType(), petDTO.breed(), petDTO.age(), null);
        return petService.createPet(pet);
    }

    @PatchMapping("/{id}")
    public Pet changePetName(@PathVariable Long id, @RequestBody String name) {
        Pet pet = petService.getPetById(id);
        pet.setName(name);
        return petService.createPet(pet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
    }
}
