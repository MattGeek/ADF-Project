package com.sdh4.no_ai_project.controllers;

import com.sdh4.no_ai_project.entities.Household;
import com.sdh4.no_ai_project.entities.Pet;
import com.sdh4.no_ai_project.services.HouseholdService;
import com.sdh4.no_ai_project.services.PetService;
import com.sdh4.no_ai_project.services.exceptions.BadDataException;
import com.sdh4.no_ai_project.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class Controller {

    private final PetService petService;
    private final HouseholdService householdService;

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/households")
    public ResponseEntity<List<Household>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @GetMapping("/households/no-pets")
    public ResponseEntity<List<Household>> getHouseholdsWithNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable long id) throws NotFoundException {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @GetMapping("/households/{eircode}")
    public ResponseEntity<Household> getHousehold(@PathVariable String eircode) throws NotFoundException {
        return ResponseEntity.ok(householdService.findHouseholdByEircode(eircode));
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/households/{eircode}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHouseholdByEircode(eircode);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/households")
    public ResponseEntity<Household> createHousehold(@RequestBody Household household) throws BadDataException {
        return ResponseEntity.ok(householdService.createHousehold(household));
    }

    @PostMapping("/pets")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) throws BadDataException {
        return ResponseEntity.ok(petService.createPet(pet));
    }

    @PatchMapping("/pets/{id}/name")
    public ResponseEntity<Pet> updatePetName(@PathVariable long id, @RequestBody String name) throws NotFoundException, BadDataException {
        return ResponseEntity.ok(petService.updatePetName(id, name));
    }
}
