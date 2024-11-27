package com.sdh4.ai_project.services;

import com.sdh4.ai_project.entities.Household;
import com.sdh4.ai_project.repositories.HouseholdRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HouseholdServiceTest {

    private final HouseholdRepository householdRepository = Mockito.mock(HouseholdRepository.class);
    private final HouseholdService householdService = new HouseholdServiceImpl(householdRepository);

    @Test
    void testFindHouseholdByEircode() {
        Household household = new Household("D02XY45", 3, 5, true, Collections.emptyList());
        when(householdRepository.findById("D02XY45")).thenReturn(Optional.of(household));

        Household result = householdService.findHouseholdByEircode("D02XY45");
        assertEquals("D02XY45", result.getEircode());
        verify(householdRepository, times(1)).findById("D02XY45");
    }
}
