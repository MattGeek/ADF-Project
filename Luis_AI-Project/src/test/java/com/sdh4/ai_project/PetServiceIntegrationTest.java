package com.sdh4.ai_project;

import com.sdh4.ai_project.entities.Household;
import com.sdh4.ai_project.entities.Pet;
import com.sdh4.ai_project.repositories.HouseholdRepository;
import com.sdh4.ai_project.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetServiceIntegrationTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Test
    void testFindPetsByAnimalType() {
        // Create and save a household
        Household household = new Household("D02XY45", 3, 5, true, null);
        householdRepository.save(household);

        // Create and save pets assigned to the household
        petRepository.save(new Pet(null, "Buddy", "Dog", "Golden Retriever", 3, household));
        petRepository.save(new Pet(null, "Mittens", "Cat", "Siamese", 2, household));

        // Find pets by animal type
        List<Pet> dogs = petRepository.findByAnimalType("Dog");

        // Assertions
        assertEquals(1, dogs.size());
        assertEquals("Buddy", dogs.getFirst().getName());
    }
}
