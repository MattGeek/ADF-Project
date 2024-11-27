package com.sdh4.ai_project.services;

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
        Pet pet = new Pet(1L, "Buddy", "Dog", "Golden Retriever", 3);
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Pet result = petService.getPetById(1L);
        assertNotNull(result);
        assertEquals("Buddy", result.getName());
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPetById_NotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PetNotFoundException.class, () -> petService.getPetById(1L));
        verify(petRepository, times(1)).findById(1L);
    }
}
