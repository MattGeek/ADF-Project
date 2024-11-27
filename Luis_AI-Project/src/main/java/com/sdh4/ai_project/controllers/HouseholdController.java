package com.sdh4.ai_project.controllers;

import com.sdh4.ai_project.dtos.HouseholdDTO;
import com.sdh4.ai_project.entities.Household;
import com.sdh4.ai_project.services.HouseholdService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @GetMapping("/nopets")
    public List<Household> getHouseholdsWithNoPets() {
        return householdService.findHouseholdsWithNoPets();
    }

    @GetMapping("/{eircode}")
    public Household getHousehold(@PathVariable String eircode, @RequestParam(defaultValue = "false") boolean withPets) {
        if (withPets) {
            return householdService.getHouseholdByIdWithPets(eircode);
        } else {
            return householdService.getHouseholdByIdNoPets(eircode);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Household createHousehold(@Valid @RequestBody HouseholdDTO householdDTO) {
        Household household = new Household(householdDTO.eircode(), householdDTO.numberOfOccupants(),
                householdDTO.maxNumberOfOccupants(), householdDTO.ownerOccupied(), null);
        return householdService.createHousehold(household);
    }

    @DeleteMapping("/{eircode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHouseholdById(eircode);
    }

    @PatchMapping("/{eircode}")
    public Household updateHouseholdDetails(@PathVariable String eircode, @Valid @RequestBody HouseholdDTO householdDTO) {
        Household household = new Household(householdDTO.eircode(), householdDTO.numberOfOccupants(),
                householdDTO.maxNumberOfOccupants(), householdDTO.ownerOccupied(), null);
        return householdService.updateHousehold(eircode, household);
    }
}
