package com.sdh4.ai_project.controllers;

import com.sdh4.ai_project.entities.User;
import com.sdh4.ai_project.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PatchMapping("/{username}/password")
    public User resetPassword(@PathVariable String username, @RequestBody String newPassword) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    @PatchMapping("/{username}/toggle-lock")
    public User toggleLock(@PathVariable String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setLocked(!user.isLocked());
        return userRepository.save(user);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userRepository.deleteById(username);
    }
}
