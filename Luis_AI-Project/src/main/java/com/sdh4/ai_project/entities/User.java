package com.sdh4.ai_project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    private String username; // Acts as email
    private String password;
    private String role;
    private boolean locked;

    private String firstName;
    private String lastName;
    private String county;
}
