package com.sdh4.ai_project.repositories;

import com.sdh4.ai_project.entities.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, String> {

    // Fetch Household by Eircode with Pets Eagerly
    @Query("SELECT h FROM Household h LEFT JOIN FETCH h.pets WHERE h.eircode = :eircode")
    Household findByEircodeWithPets(String eircode);

    // Find Households with No Pets
    @Query("SELECT h FROM Household h WHERE h.pets IS EMPTY")
    List<Household> findHouseholdsWithNoPets();
}
