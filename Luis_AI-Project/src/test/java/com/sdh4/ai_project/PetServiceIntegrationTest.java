package com.sdh4.ai_project;

import com.sdh4.ai_project.entities.Pet;
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

    @Test
    void testFindPetsByAnimalType() {
        petRepository.save(new Pet(null, "Buddy", "Dog", "Golden Retriever", 3));
        petRepository.save(new Pet(null, "Mittens", "Cat", "Siamese", 2));

        List<Pet> dogs = petRepository.findByAnimalType("Dog");
        assertEquals(1, dogs.size());
        assertEquals("Buddy", dogs.getFirst().getName());
    }
}
