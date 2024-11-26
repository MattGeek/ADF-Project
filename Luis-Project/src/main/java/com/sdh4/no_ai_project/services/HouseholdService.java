package com.sdh4.no_ai_project.services;

import com.sdh4.no_ai_project.entities.Household;

import java.util.List;

public interface HouseholdService {
    List<Household> getAllHouseholds();
    Household findHouseholdByEircode(String eircode);
    List<Household> findHouseholdsWithNoPets();
    Household createHousehold(Household household);
    void deleteHouseholdByEircode(String eircode);
}