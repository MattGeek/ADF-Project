package com.sdh4.ai_project.graphql;

import com.sdh4.ai_project.dtos.HouseholdDTO;
import com.sdh4.ai_project.entities.Household;
import com.sdh4.ai_project.services.HouseholdService;
import com.sdh4.ai_project.services.PetService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_ADMIN")
    public Household createHousehold(HouseholdDTO input) {
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
    @Secured("ROLE_ADMIN")
    public Boolean deleteHouseholdById(String eircode) {
        householdService.deleteHouseholdById(eircode);
        return true;
    }

    @MutationMapping
    @Secured("ROLE_ADMIN")
    public Boolean deletePetById(Long id) {
        petService.deletePetById(id);
        return true;
    }
}
