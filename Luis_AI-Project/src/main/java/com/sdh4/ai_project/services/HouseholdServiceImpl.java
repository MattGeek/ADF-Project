package com.sdh4.ai_project.services;

import com.sdh4.ai_project.entities.Household;
import com.sdh4.ai_project.entities.Pet;
import com.sdh4.ai_project.exceptions.HouseholdNotFoundException;
import com.sdh4.ai_project.repositories.HouseholdRepository;
import com.sdh4.ai_project.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;
    private final PetRepository petRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository, PetRepository petRepository) {
        this.householdRepository = householdRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household getHouseholdByIdNoPets(String eircode) {
        return householdRepository.findById(eircode)
                .orElseThrow(() -> new HouseholdNotFoundException("Household not found with Eircode: " + eircode));
    }

    @Override
    public Household getHouseholdByIdWithPets(String eircode) {
        return householdRepository.findByEircodeWithPets(eircode);
    }

    @Override
    public Household updateHousehold(String eircode, Household householdDetails) {
        Household existingHousehold = householdRepository.findById(eircode)
                .orElseThrow(() -> new HouseholdNotFoundException("Household not found with Eircode: " + eircode));

        existingHousehold.setNumberOfOccupants(householdDetails.getNumberOfOccupants());
        existingHousehold.setMaxNumberOfOccupants(householdDetails.getMaxNumberOfOccupants());
        existingHousehold.setOwnerOccupied(householdDetails.isOwnerOccupied());

        return householdRepository.save(existingHousehold);
    }

    @Override
    public void deleteHouseholdById(String eircode) {
        Household household = householdRepository.findById(eircode)
                .orElseThrow(() -> new HouseholdNotFoundException("Household not found with Eircode: " + eircode));

        petRepository.deleteAll(household.getPets());
        householdRepository.delete(household);
    }

    @Override
    public void deletePetsByName(String name) {
        List<Pet> pets = petRepository.findByNameIgnoreCase(name);
        petRepository.deleteAll(pets);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findAll().stream()
                .filter(Household::isOwnerOccupied)
                .collect(Collectors.toList());
    }

    @Override
    public String getHouseholdStatistics() {
        long emptyHouses = householdRepository.findAll().stream()
                .filter(h -> h.getNumberOfOccupants() == 0)
                .count();

        long fullHouses = householdRepository.findAll().stream()
                .filter(h -> h.getNumberOfOccupants() == h.getMaxNumberOfOccupants())
                .count();

        return "Number of empty houses: " + emptyHouses + ", Number of full houses: " + fullHouses;
    }
}
