package com.sdh4.no_ai_project.controllers;

import com.sdh4.no_ai_project.entities.User;
import com.sdh4.no_ai_project.services.UserService;
import com.sdh4.no_ai_project.services.exceptions.BadDataException;
import com.sdh4.no_ai_project.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws BadDataException {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PatchMapping("/{id}/reset-password")
    public ResponseEntity<User> resetPassword(@PathVariable Long id, @RequestBody String newPassword) throws NotFoundException, BadDataException {
        return ResponseEntity.ok(userService.resetPassword(id, newPassword));
    }

    @PatchMapping("/{id}/toggle-unlocked")
    public ResponseEntity<User> toggleUnlocked(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(userService.toggleUnlocked(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws NotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
