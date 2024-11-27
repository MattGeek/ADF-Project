package com.sdh4.ai_project.graphql;

import com.sdh4.ai_project.dtos.HouseholdDTO;
import com.sdh4.ai_project.entities.Household;
import com.sdh4.ai_project.services.HouseholdService;
import com.sdh4.ai_project.services.PetService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationController {

    private final HouseholdService householdService;
    private final PetService petService;

    public MutationController(HouseholdService householdService, PetService petService) {
        this.householdService = householdService;
        this.petService = petService;
    }

    @MutationMapping
    public Household createHousehold(HouseholdInput input) {
        Household household = new Household(
                input.eircode(),
                input.numberOfOccupants(),
                input.maxNumberOfOccupants(),
                input.ownerOccupied(),
                null
        );
        return householdService.createHousehold(household);
    }

    @MutationMapping
    public Boolean deleteHouseholdById(String eircode) {
        householdService.deleteHouseholdById(eircode);
        return true;
    }

    @MutationMapping
    public Boolean deletePetById(Long id) {
        petService.deletePetById(id);
        return true;
    }

    record HouseholdInput(String eircode, int numberOfOccupants, int maxNumberOfOccupants, Boolean ownerOccupied) {}
}
