package com.example.demo_claude;

import com.example.demo_claude.entities.Pet;
import com.example.demo_claude.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PetRepositoryTests {

    @Autowired
    private PetRepository petRepository;

    @Test
    void shouldSaveAndFindPetByName() {
        // Arrange
        Pet pet = Pet.builder()
                .name("Buddy")
                .animalType("Dog")
                .breed("Golden Retriever")
                .age(3)
                .build();

        // Act
        Pet savedPet = petRepository.save(pet);
        Optional<Pet> foundPet = petRepository.findByName("Buddy");

        // Assert
        assertThat(foundPet).isPresent();
        assertThat(foundPet.get().getId()).isEqualTo(savedPet.getId());
        assertThat(foundPet.get().getName()).isEqualTo("Buddy");
    }

    @Test
    void shouldFindPetsByAnimalTypeIgnoringCase() {
        // Arrange
        Pet dog = Pet.builder()
                .name("Buddy")
                .animalType("Dog")
                .breed("Golden Retriever")
                .age(3)
                .build();
        Pet cat = Pet.builder()
                .name("Mittens")
                .animalType("CAT")
                .breed("Siamese")
                .age(2)
                .build();
        petRepository.save(dog);
        petRepository.save(cat);

        // Act
        List<Pet> dogs = petRepository.findByAnimalTypeIgnoreCase("dog");
        List<Pet> cats = petRepository.findByAnimalTypeIgnoreCase("CAT");

        // Assert
        assertThat(dogs).hasSize(1);
        assertThat(dogs.get(0).getName()).isEqualTo("Buddy");
        assertThat(cats).hasSize(1);
        assertThat(cats.get(0).getName()).isEqualTo("Mittens");
    }

    // Add more repository tests as needed
}
