package com.sdh4.no_ai_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "household")
@AllArgsConstructor
@NoArgsConstructor
public class Household {
    @Id
    private String eircode;

    private int numberOfOccupants;
    private int maxNumberOfOccupants;
    private boolean ownerOccupied;

    @OneToMany(mappedBy = "household")
    private List<Pet> pets;
}
