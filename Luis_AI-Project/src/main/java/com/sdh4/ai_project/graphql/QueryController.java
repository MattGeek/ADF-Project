package com.sdh4.ai_project.graphql;

import com.sdh4.ai_project.dtos.StatisticDTO;
import com.sdh4.ai_project.entities.Household;
import com.sdh4.ai_project.entities.Pet;
import com.sdh4.ai_project.services.HouseholdService;
import com.sdh4.ai_project.services.PetService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QueryController {

    private final HouseholdService householdService;
    private final PetService petService;

    public QueryController(HouseholdService householdService, PetService petService) {
        this.householdService = householdService;
        this.petService = petService;
    }

    @QueryMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public StatisticDTO getStatistic() {
        String stats = householdService.getHouseholdStatistics();
        String[] parts = stats.replace("Number of empty houses: ", "")
                .replace("Number of full houses: ", "").split(", ");
        return new StatisticDTO(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
    }

    @QueryMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    public List<Pet> getPetsByAnimalType(String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    public Household getHousehold(String eircode) {
        return householdService.getHouseholdByIdWithPets(eircode);
    }

    @QueryMapping
    public Pet getPet(Long id) {
        return petService.getPetById(id);
    }
}
