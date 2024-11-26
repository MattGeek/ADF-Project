package com.sdh4.no_ai_project.models;

import lombok.Data;

@Data
public class HouseholdInput {
    private String eircode;
    private int numberOfOccupants;
    private int maxNumberOfOccupants;
    private boolean ownerOccupied;
}
