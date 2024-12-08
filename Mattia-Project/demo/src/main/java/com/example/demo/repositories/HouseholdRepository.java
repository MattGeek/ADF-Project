package com.example.demo.repositories;

import com.example.demo.entities.Household;
import com.example.demo.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, String> {
}
