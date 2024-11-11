package com.sdh4.no_ai_project;

import com.sdh4.no_ai_project.entities.Pet;
import com.sdh4.no_ai_project.services.PetService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PetServiceTests {
	@Autowired
	private PetService petService;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(petService);
	}

	@Order(1)
	@Test
	void savePet() {
		Pet pet = new Pet();
		pet.setName("test");
		pet.setAnimalType("test");
		pet.setBreed("test");
		pet.setAge(1);

		pet = petService.createPet(pet);

		Assertions.assertNotNull(pet);
		Assertions.assertEquals("test", pet.getName());
	}

	@Order(2)
	@Test
	void deletePetsByName() {
		int rowsAffected = petService.deletePetsByName("test");
		Assertions.assertEquals(1, rowsAffected);
	}

	@Test
	void getAllPets() {
		List<Pet> pets = petService.getAllPets();
		Assertions.assertNotNull(pets);
	}

	@Test
	void getPetById() {
		Pet pet = petService.getPetById(1);
		Assertions.assertNotNull(pet);
	}
}
