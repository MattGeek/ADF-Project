package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name="household")
@AllArgsConstructor
@NoArgsConstructor
public class Household {
    @Id
    private String eircode;
    private int numberOfOccupants;
    private int maxNumberOfOccupants;
    private boolean ownerOccupied;

    @OneToMany(mappedBy="household", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @ToString.Exclude
    private List<Pet> pets;
}
