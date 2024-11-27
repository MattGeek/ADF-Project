package com.sdh4.ai_project.services;

import com.sdh4.ai_project.entities.Household;
import com.sdh4.ai_project.entities.Pet;
import com.sdh4.ai_project.exceptions.PetNotFoundException;
import com.sdh4.ai_project.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceTest {

    private final PetRepository petRepository = Mockito.mock(PetRepository.class);
    private final PetService petService = new PetServiceImpl(petRepository);

    @Test
    void testGetPetById_Found() {
        // Create a mock household
        Household household = new Household("D02XY45", 3, 5, true, null);

        // Create a mock pet
        Pet pet = new Pet(1L, "Buddy", "Dog", "Golden Retriever", 3, household);

        // Mock repository behavior
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        // Call the service method
        Pet result = petService.getPetById(1L);

        // Assertions
        assertNotNull(result);
        assertEquals("Buddy", result.getName());
        assertEquals("Golden Retriever", result.getBreed());
        assertEquals(household, result.getHousehold());

        // Verify repository interaction
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPetById_NotFound() {
        // Mock repository behavior
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and assert exception
        assertThrows(PetNotFoundException.class, () -> petService.getPetById(1L));

        // Verify repository interaction
        verify(petRepository, times(1)).findById(1L);
    }
}
