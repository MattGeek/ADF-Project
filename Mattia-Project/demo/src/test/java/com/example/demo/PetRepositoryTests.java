package com.example.demo;

import com.example.demo.entities.Pet;
import com.example.demo.repositories.HouseholdRepository;
import com.example.demo.repositories.PetRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(1)
public class PetRepositoryTests {
    @Autowired
    PetRepository repository;
    @Autowired
    HouseholdRepository householdRepository;

    @Test
    void contextLoads(){
        Assertions.assertNotNull(repository);
        Assertions.assertNotNull(householdRepository);
    }

    @Nested
    @Order(1)
    class TestGets{
        @Test
        void findPets_shouldReturnPets(){
            int count = repository.findAll().size();
            Assertions.assertEquals(10, count);
        }

        @Test
        void findPetById_shouldReturnPet(){
            Optional<Pet> opt = repository.findById((long) 1);
            Assertions.assertTrue(opt.isPresent());
            Assertions.assertNotNull(opt.get());
            Assertions.assertEquals("Buddy", opt.get().getName());
        }

        @Test
        void findPetsByAnimalType_shouldReturnPets(){
            int count = repository.findPetsByAnimalType("Dog").size();
            Assertions.assertEquals(3, count);
        }

        @Test
        void findPetsByBreed_shouldReturnPets(){
            int count = repository.findPetsByBreed("Siamese").size();
            Assertions.assertEquals(1, count);
        }
    }

    @Nested
    @Order(2)
    class TestCreate{
        @Test
        void createPet_shouldCreatePet(){
            Pet pet = new Pet(
            "Cindy",                  // Name
                "Cat",                      // AnimalType
                "American Wirehair",        // Breed
                2,                           //Age
                householdRepository.findById("D02XY45").get()
            );
            pet = repository.save(pet);
            Assertions.assertNotNull(pet);
            Assertions.assertEquals(11, pet.getId());
        }
    }

    @Nested
    @Order(3)
    class TestUpdate{
        @Test
        void updatePetDetails_shouldUpdatePet(){
            repository.updatePetDetails(11, "Carmen", 3);
            Pet pet = repository.findById((long) 11).get();
            Assertions.assertEquals(pet.getName(), "Carmen");
            Assertions.assertEquals(pet.getAge(), 3);
        }
    }

    @Nested
    @Order(4)
    class TestDelete{
        @Test
        void deletePetById_shouldDeletePet(){
            repository.deleteById((long) 11);
            int count = repository.findAll().size();
            Assertions.assertEquals(10, count);
        }
    }
}
