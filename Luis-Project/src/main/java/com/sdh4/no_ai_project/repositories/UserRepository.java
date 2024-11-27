package com.sdh4.no_ai_project.repositories;

import com.sdh4.no_ai_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
