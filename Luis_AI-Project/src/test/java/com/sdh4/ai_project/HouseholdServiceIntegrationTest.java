package com.sdh4.ai_project;

import com.sdh4.ai_project.entities.Household;
import com.sdh4.ai_project.repositories.HouseholdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HouseholdServiceIntegrationTest {

    @Autowired
    private HouseholdRepository householdRepository;

    @Test
    void testFindHouseholdsWithNoPets() {
        List<Household> households = householdRepository.findHouseholdsWithNoPets();
        assertNotNull(households);
        assertFalse(households.isEmpty());
    }
}
