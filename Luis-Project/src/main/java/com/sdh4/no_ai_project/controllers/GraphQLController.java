package com.sdh4.no_ai_project.controllers;

import com.sdh4.no_ai_project.entities.Household;
import com.sdh4.no_ai_project.entities.Pet;
import com.sdh4.no_ai_project.models.AnimalTypeCount;
import com.sdh4.no_ai_project.models.HouseholdInput;
import com.sdh4.no_ai_project.models.Statistics;
import com.sdh4.no_ai_project.services.HouseholdService;
import com.sdh4.no_ai_project.services.PetService;
import com.sdh4.no_ai_project.services.exceptions.BadDataException;
import com.sdh4.no_ai_project.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLController {
    private final HouseholdService householdService;
    private final PetService petService;

    @QueryMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    public List<Pet> getAllPetsByAnimalType(@Argument String animalType) throws BadDataException {
        return petService.getPetsByType(animalType);
    }

    @QueryMapping
    public Household getHousehold(@Argument String eircode) throws NotFoundException {
        return householdService.findHouseholdByEircode(eircode);
    }

    @QueryMapping
    public Pet getPet(@Argument Long id) throws NotFoundException {
        return petService.getPetById(id);
    }

    @QueryMapping
    public Statistics getStatistics() {
        int totalHouseholds = householdService.getAllHouseholds().size();
        int totalPets = petService.getAllPets().size();
        List<AnimalTypeCount> petsByAnimalType = petService.getPetsByAnimalTypeStats();

        return new Statistics(totalHouseholds, totalPets, petsByAnimalType);
    }

    @MutationMapping
    public Household createHousehold(@Argument HouseholdInput input) throws BadDataException {
        Household household = new Household(input.getEircode(), input.getNumberOfOccupants(), input.getMaxNumberOfOccupants(), input.isOwnerOccupied(), null);
        return householdService.createHousehold(household);
    }

    @MutationMapping
    public Boolean deleteHousehold(@Argument String eircode) {
        householdService.deleteHouseholdByEircode(eircode);
        return true;
    }

    @MutationMapping
    public Boolean deletePet(@Argument Long id) {
        petService.deletePetById(id);
        return true;
    }
}
