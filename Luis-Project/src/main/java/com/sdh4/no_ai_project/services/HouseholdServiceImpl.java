package com.sdh4.no_ai_project.services;

import com.sdh4.no_ai_project.entities.Household;
import com.sdh4.no_ai_project.repositories.HouseholdRepository;
import com.sdh4.no_ai_project.services.exceptions.BadDataException;
import com.sdh4.no_ai_project.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household findHouseholdByEircode(String eircode) throws NotFoundException {
        return householdRepository.findById(eircode)
                .orElseThrow(() -> new NotFoundException("No household found with Eircode: " + eircode));
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public Household createHousehold(Household household) throws BadDataException {
        if (household == null || household.getEircode() == null || household.getEircode().isEmpty()) {
            throw new BadDataException("Invalid household data: Eircode cannot be null or empty");
        }
        return householdRepository.save(household);
    }

    @Override
    public void deleteHouseholdByEircode(String eircode) throws NotFoundException {
        Household household = householdRepository.findById(eircode)
                .orElseThrow(() -> new NotFoundException("No household found with Eircode: " + eircode));
        householdRepository.delete(household);
    }
}
