package com.example.demo_claude;

import com.example.demo_claude.repositories.dto.PetDTO;
import com.example.demo_claude.entities.Pet;
import com.example.demo_claude.services.PetServiceImpl;
import com.example.demo_claude.exceptions.PetAlreadyExistsException;
import com.example.demo_claude.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTests {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @Test
    void shouldCreateNewPet() throws PetAlreadyExistsException {
        // Arrange
        PetDTO petDTO = new PetDTO(null, "Buddy", "Dog", "Golden Retriever", 3);
        when(petRepository.findByName("Buddy")).thenReturn(Optional.empty());
        when(petRepository.save(any(Pet.class))).thenReturn(new Pet(1L, "Buddy", "Dog", "Golden Retriever", 3));

        // Act
        PetDTO createdPet = petService.createPet(petDTO);

        // Assert
        assertThat(createdPet.getId()).isEqualTo(1L);
        assertThat(createdPet.getName()).isEqualTo("Buddy");
        verify(petRepository, times(1)).findByName("Buddy");
        verify(petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    void shouldThrowExceptionWhenPetAlreadyExists() {
        // Arrange
        PetDTO petDTO = new PetDTO(null, "Buddy", "Dog", "Golden Retriever", 3);
        when(petRepository.findByName("Buddy")).thenReturn(Optional.of(new Pet()));

        // Act & Assert
        assertThatThrownBy(() -> petService.createPet(petDTO))
                .isInstanceOf(PetAlreadyExistsException.class)
                .hasMessage("Pet with name Buddy already exists.");
        verify(petRepository, times(1)).findByName("Buddy");
    }

    @Test
    void shouldGetAllPets() {
        // Arrange
        List<Pet> pets = Arrays.asList(
                new Pet(null, "Buddy", null, null, -1),
                new Pet(null, "Mittens", null, null, -1),
                new Pet(null, "Charlie", null, null, -1)
        );
        when(petRepository.findAll()).thenReturn(pets);

        // Act
        List<PetDTO> petDTOs = petService.getAllPets();

        // Assert
        assertThat(petDTOs).hasSize(3);
        assertThat(petDTOs.stream().map(PetDTO::getName))
                .containsExactlyInAnyOrder("Buddy", "Mittens", "Charlie");
        verify(petRepository, times(1)).findAll();
    }

    // Add more service tests as needed
}
