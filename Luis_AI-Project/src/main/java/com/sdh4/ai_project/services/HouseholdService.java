package com.sdh4.ai_project.services;

import com.sdh4.ai_project.entities.Household;

import java.util.List;

public interface HouseholdService {
    Household createHousehold(Household household);

    List<Household> getAllHouseholds();

    Household getHouseholdByIdNoPets(String eircode);

    Household getHouseholdByIdWithPets(String eircode);

    Household updateHousehold(String eircode, Household householdDetails);

    void deleteHouseholdById(String eircode);

    void deletePetsByName(String name);

    List<Household> findHouseholdsWithNoPets();

    List<Household> findOwnerOccupiedHouseholds();

    String getHouseholdStatistics();
}
