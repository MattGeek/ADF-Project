package com.sdh4.no_ai_project.repositories;

import com.sdh4.no_ai_project.entities.Pet;
import com.sdh4.no_ai_project.models.AnimalTypeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query
    List<Pet> getPetsByAnimalType(@Param("type") String type);

    @Query
    List<Pet> getPetsByBreed(@Param("breed") String breed);

    @Query("SELECT new com.sdh4.no_ai_project.models.AnimalTypeCount(p.animalType, COUNT(p)) " + "FROM Pet p GROUP BY p.animalType")
    List<AnimalTypeCount> getPetsGroupedByAnimalType();

    @Modifying
    @Query("UPDATE Pet p SET p.name=:newName WHERE p.id=:id")
    int updatePetName(@Param("id") Long id, @Param("newName") String newName);

    @Modifying
    @Query("UPDATE Pet p SET p.animalType=:newType WHERE p.id=:id")
    int updatePetType(@Param("id") Long id, @Param("newType") String newType);

    @Modifying
    @Query("UPDATE Pet p SET p.breed=:newBreed WHERE p.id=:id")
    int updatePetBreed(@Param("id") Long id, @Param("newBreed") String newBreed);

    @Modifying
    @Query("UPDATE Pet p SET p.age=:newAge WHERE p.id=:id")
    int updatePetAge(@Param("id") Long id, @Param("newAge") int newAge);

    int deletePetsByName(@Param("name") String name);
}
